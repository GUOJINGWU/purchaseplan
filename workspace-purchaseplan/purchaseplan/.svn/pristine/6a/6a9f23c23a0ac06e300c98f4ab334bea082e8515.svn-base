/**  
 * @Title: ResponseWrapper.java
 * @Package com.youzhicai.purchaseplan.business.filter
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月27日 下午5:12:52
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.business.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @ClassName: ResponseWrapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月27日 下午5:12:52
 */
public class ResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream baos;
    private ServletOutputStream sos;

    public ResponseWrapper(HttpServletResponse httpServletResponse) {
        super(httpServletResponse);
        baos = new ByteArrayOutputStream();
        sos = new WrapperOutputStream(baos);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return sos;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (sos != null) {
            sos.flush();
        }
    }

    public byte[] getContent() throws IOException {
        flushBuffer();
        return baos.toByteArray();
    }

    class WrapperOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream baos;

        public WrapperOutputStream(ByteArrayOutputStream baos) {
            this.baos = baos;
        }

        @Override
        public void write(int b) throws IOException {
            baos.write(b);
        }

        @Override
        public boolean isReady() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void setWriteListener(WriteListener arg0) {
            // TODO Auto-generated method stub
        }
    }
}
