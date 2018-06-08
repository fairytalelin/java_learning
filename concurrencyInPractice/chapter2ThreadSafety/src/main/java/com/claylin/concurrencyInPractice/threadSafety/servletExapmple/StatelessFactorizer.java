package com.claylin.concurrencyInPractice.threadSafety.servletExapmple;

import com.claylin.concurrencyInPractice.annotation.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;

/**
 * 无状态对象永远是线程安全的
 */
@ThreadSafe
public class StatelessFactorizer implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
