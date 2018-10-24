package com.youzhicai.purchaseplan.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class HttpUtils {

    /**
     * 将cookie封装到Map里面
     * 
     * @param request
     * @return
     */
    public static Map<String, String> ReadCookieMap(HttpServletRequest request) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }

    public static boolean IsCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @Title: postUrlObjByModel
     * @param postUrl 请求地址
     * @param obj 参数
     * @param clazz 返回Model类型
     * @throws Exception
     * @return T
     */
    public static <T> T postUrlObjByModel(String postUrl, Object obj, Class<T> clazz) throws Exception {
        String resultJson = postByModel(postUrl, obj);
        return JSON.parseObject(resultJson, clazz);
    }

    /**
     * @Title: postUrlListByModel
     * @param postUrl 请求地址
     * @param obj 参数
     * @param clazz 返回Model类型
     * @throws Exception
     * @return List<T>
     */
    public static <T> List<T> postUrlListByModel(String postUrl, Object obj, Class<T> clazz) throws Exception {
        String resultJson = postByModel(postUrl, obj);
        return JSON.parseArray(resultJson, clazz);
    }

    /**
     * @Title: getUrlObjByModel
     * @param getUrl 请求地址
     * @param clazz 返回Model类型
     * @throws Exception
     * @return T
     */
    public static <T> T getUrlObjByModel(String getUrl, Class<T> clazz) throws Exception {
        String resultJson = getByModel(getUrl);
        return JSON.parseObject(resultJson, clazz);
    }

    /**
     * @Title: getUrlListByModel
     * @param getUrl 请求地址
     * @param clazz 返回Model类型
     * @throws Exception
     * @return List<T>
     */
    public static <T> List<T> getUrlListByModel(String getUrl, Class<T> clazz) throws Exception {
        String resultJson = getByModel(getUrl);
        return JSON.parseArray(resultJson, clazz);
    }

    /*
     * 封装HttpPost参数
     */
    private static String postByModel(String postUrl, Object obj) throws Exception {
        HttpPost httpPost = new HttpPost(postUrl);
        httpPost.setEntity(new StringEntity(JSON.toJSONString(obj), "UTF-8"));
        return sendHttpRequest(httpPost);
    }

    /*
     * 封装HttpGet参数
     */
    private static String getByModel(String getUrl) throws Exception {
        return sendHttpRequest(new HttpGet(getUrl));
    }

    /*
     * 发送Http请求
     */
    private static String sendHttpRequest(HttpRequestBase hrb) throws Exception {
        hrb.setHeader("Content-Type", "application/json; charset=UTF-8");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse result = httpClient.execute(hrb);
        String out = EntityUtils.toString(result.getEntity());
        httpClient.close();
        return out;
    }

}
