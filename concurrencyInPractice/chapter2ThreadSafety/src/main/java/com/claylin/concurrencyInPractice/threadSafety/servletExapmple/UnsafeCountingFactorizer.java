package com.claylin.concurrencyInPractice.threadSafety.servletExapmple;

import javax.servlet.*;
import java.io.IOException;

public class UnsafeCountingFactorizer implements Servlet {
    private long count = 0;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        ++count;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
