/**  
 * @Title: LoginAuthInterceptor.java
 * @Package com.youzhicai.purchaseplan.web.interceptor
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月21日 上午9:03:08
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.youzhicai.purchaseplan.handler.HandlerClasses;
import com.youzhicai.purchaseplan.web.domain.AuthInfo;
import com.youzhicai.purchaseplan.web.domain.AuthSubs;
import com.youzhicai.purchaseplan.web.domain.AuthSubsidiary;
import com.youzhicai.purchaseplan.web.domain.CookieUsers;
import com.youzhicai.purchaseplan.web.domain.OrganizeModel;
import com.youzhicai.purchaseplan.web.util.ConfigUtil;
import com.youzhicai.purchaseplan.web.util.DESUtil;
import com.youzhicai.purchaseplan.web.util.HttpUtils;
import com.youzhicai.purchaseplan.web.util.StringUtil;

/**
 * @ClassName: LoginAuthInterceptor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月21日 上午9:03:08
 */
public class LoginAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (HandlerClasses.WEB_METHODS_UNLOGIN.contains(method)) {
            // 不需要登录验证
            return true;
        }

        // 1.判断该域名下是否有cookie,没有跳转到登录页面
        if (HttpUtils.IsCookie(request)) {
            jump2Login(request, response, "帐号未登录,请重新登录!!");
            return false;
        }

        // 当前域名下cookie用户信息
        CookieUsers cookieUsers = ReadCookieUsers(request);
        if (cookieUsers == null || StringUtil.isNullOrEmpty(cookieUsers.getSsoInfo())) {
            jump2Login(request, response, "帐号未登录,请重新登录!!");
            return false;
        }

        // Session中没有保存用户信息时候,将对用户进行验证操作
        if (request.getSession().getAttribute(ConfigUtil.getValue("AuthInfo")) == null) {
            if (!updateSessionUser(request, response)) {
                jump2Login(request, response, "登录超时,请重新登录(1)!!");
                return false;
            }
        } else {
            // 验证session里用户账号 跟cookie里面是否一致，如果不一致 更新session
            AuthInfo sessionAuthInfo = (AuthInfo) request.getSession().getAttribute(ConfigUtil.getValue("AuthInfo"));
            if (sessionAuthInfo == null) {
                jump2Login(request, response, "登录超时,请重新登录(2)!!");
                return false;
            }

            if (sessionAuthInfo.getCookie() == null || !cookieUsers.getSsoInfo().equals(sessionAuthInfo.getCookie().getSsoInfo())) {
                if (!updateSessionUser(request, response)) {
                    jump2Login(request, response, "登录超时,请重新登录(3)!!");
                    return false;
                }
            }
        }

        return true;
    }

    private void jump2Login(HttpServletRequest request, HttpServletResponse response, String message) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String basePath = ConfigUtil.getValue("YouzcLoginUrl");
        PrintWriter out = response.getWriter();
        StringBuilder builder = new StringBuilder();
        builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
        // builder.append("alert(\"" + message + "\");");
        builder.append("window.top.location.href=\"");
        builder.append(basePath);
        builder.append("\";</script>");
        out.print(builder.toString());
        out.close();
    }

    private AuthInfo getLogin(HttpServletRequest request, HttpServletResponse response) {
        AuthInfo info = new AuthInfo();
        CookieUsers cock = ReadCookieUsers(request);
        try {
            info = initAuth(info, cock);
        } catch (Exception e) {
            info = new AuthInfo();
            info.setState(false);
            e.printStackTrace();
        }
        return info;
    }

    private AuthInfo initAuth(AuthInfo info, CookieUsers cock) throws Exception {
        String sessionUserName;// sessionUserName
        if ((sessionUserName = desInfo(cock.getSessionUserName())) != null) {
            info.setUserId(sessionUserName);
        } else {
            throwError();
        }

        String companyId;// companyId
        if ((companyId = desInfo(cock.getCompanyId())) != null) {
            info.setUser_ID(companyId);
        } else {
            throwError();
        }

        String site;// site
        if ((site = desInfo(cock.getSite())) != null) {
            info.setUserType(site);
        } else {
            throwError();
        }

        // isShowMsg[unUsed]

        String sessionKey;// sessionKey
        if ((sessionKey = cock.getSessionKey()) != null) {
            info.setSessionKey(sessionKey);
        } else {
            throwError();
        }

        String sso;// ssoInfo
        if ((sso = desInfo(cock.getSsoInfo())) != null) {
            String[] ssoInfos = sso.split("&\\+");

            if (ssoInfos.length == 10 && sso.endsWith("&+")) {
                info.setEmail("");
            } else if (ssoInfos.length == 11) {
                info.setEmail(ssoInfos[10]);
            } else {
                throwError();
            }

            info.setBaseUrl(ssoInfos[0]);
            info.setDeviceSeriesNum(ssoInfos[1]);
            info.setUserName(ssoInfos[2]);
            info.setCertEndDate(ssoInfos[3]);
            info.setLastLoginTime(ssoInfos[4]);
            info.setLastLoginIP(ssoInfos[5]);
            info.setIsMain(ssoInfos[6]);
            info.setPhoneNumber(ssoInfos[7]);
            info.setTelPhone(ssoInfos[8]);
            info.setSubName(ssoInfos[9]);
        } else {
            throwError();
        }

        // name[unUsed]

        String sessionId;// sessionId
        if ((sessionId = desInfo(cock.getSessionId())) != null) {
            info.setId(sessionId);
        } else {
            throwError();
        }

        String randomNum;// randomNum
        if ((randomNum = desInfo(cock.getRandomNum())) != null) {
            info.setRandomNum(randomNum);
        } else {
            throwError();
        }

        info.setCookie(cock);
        info.setState(true);

        // 读取默认权限
        String[] permissions = ConfigUtil.getValue("menuTag").split(",");
        if (permissions.length > 0) {
            HashMap<Integer, HashMap<Integer, ArrayList<String>>> per = new HashMap<Integer, HashMap<Integer, ArrayList<String>>>();
            for (int i = 0; i < permissions.length; i++) {
                HashMap<Integer, ArrayList<String>> hashMap = new HashMap<Integer, ArrayList<String>>();
                hashMap.put(0, null);
                per.put(Integer.parseInt(permissions[i]), hashMap);
            }
            info.setPermissions(per);
        }

        try {
            String apiUrl = ConfigUtil.getValue("memberAPIUrl") + "/api/AccountMan/getlistbycompanyid";
            List<OrganizeModel> companyList = HttpUtils.postUrlListByModel(apiUrl, info.getUser_ID(), OrganizeModel.class);
            System.err.println("-1--->" + JSON.toJSONString(companyList));
            List<AuthSubsidiary> authSubsidiaries = new ArrayList<AuthSubsidiary>(companyList.size());
            for (OrganizeModel oModel : companyList) {
                authSubsidiaries.add(new AuthSubsidiary(oModel.getiD(), oModel.getName()));
            }
            info.setSubsidiarieList(authSubsidiaries);

            String childUrl = ConfigUtil.getValue("dataBusApiUrl") + "/user/GetParentChildCompanyIdAndNameByUserId";
            AuthSubs oList = HttpUtils.postUrlObjByModel(childUrl, info.getId(), AuthSubs.class);
            if (oList != null) {
                info.setSubs(oList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    private String desInfo(String data) throws Exception {
        byte[] decrypt = DESUtil.decrypt(data);
        return new String(decrypt, "GBK");
    }

    private void throwError() {
        throw new RuntimeException("AuthInfo 参数 DES解密解析 异常");
    }

    /**
     * 将cookie封装到CookieUsers里面
     * 
     * @param request
     * @return
     */
    private CookieUsers ReadCookieUsers(HttpServletRequest request) {
        CookieUsers users = new CookieUsers();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if ("CompanyID".equals(cookie.getName())) {
                    users.setCompanyId(cookie.getValue());
                }
                if ("IsShowMsg".equals(cookie.getName())) {
                    users.setIsShowMsg(cookie.getValue());
                }
                if ("Name".equals(cookie.getName())) {
                    users.setName(cookie.getValue());
                }
                if ("RandomNum".equals(cookie.getName())) {
                    users.setRandomNum(cookie.getValue());
                }
                if ("SessionId".equals(cookie.getName())) {
                    users.setSessionId(cookie.getValue());
                }
                if ("SessionKey".equals(cookie.getName())) {
                    users.setSessionKey(cookie.getValue());
                }
                if ("SessionUserName".equals(cookie.getName())) {
                    users.setSessionUserName(cookie.getValue());
                }
                if ("Site".equals(cookie.getName())) {
                    users.setSite(cookie.getValue());
                }
                if ("SSOInfo".equals(cookie.getName())) {
                    users.setSsoInfo(cookie.getValue());
                }
            }
        }
        return users;
    }

    /**
     * 更新session中用户信息
     */
    private boolean updateSessionUser(HttpServletRequest request, HttpServletResponse response) {
        // 2.cookie,存在的话,调用场景接口对用户进行校验.
        AuthInfo info = getLogin(request, response);

        // 3.验证失败时候,跳转到到登录主页.
        if (!info.isState()) {
            return false;
        }
        // 4.验证成功之后,替换就的Session信息
        // 若存在会话则返回该会话，否则返回NULL
        HttpSession sessionOld = request.getSession(false);
        if (sessionOld != null) {
            sessionOld.invalidate();
        }

        // 若存在会话则返回该会话，否则新建一个会话。
        HttpSession session = request.getSession(true);
        session.setAttribute(ConfigUtil.getValue("AuthInfo"), info);
        return true;
    }

}
