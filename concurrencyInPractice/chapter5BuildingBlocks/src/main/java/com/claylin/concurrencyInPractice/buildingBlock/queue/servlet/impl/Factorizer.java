package com.claylin.concurrencyInPractice.buildingBlock.queue.servlet.impl;

import com.claylin.concurrencyInPractice.buildingBlock.queue.servlet.Computable;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

public class Factorizer implements Servlet {
    private final Computable<BigInteger, BigInteger[]> c = new Computable<BigInteger, BigInteger[]>() {
        @Override
        public BigInteger[] compute(BigInteger arg) throws InterruptedException {
            return new BigInteger[0];
        }
    };

    private final Computable<BigInteger, BigInteger[]> cache = new Memorizer<>(c);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //do service
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
