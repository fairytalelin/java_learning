package com.claylin.springboot.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Configuration
public class WebConfiguration {

    @Bean
    public MyFilter myFilter() {
        return new MyFilter();
    }

    @Bean
    public FilterRegistrationBean registrationBean(MyFilter myFilter, ClaylinProperties properties) {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<Filter>();
        registrationBean.setFilter(myFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("name", "claylin");
        registrationBean.setName("MyFilter");
        registrationBean.setOrder(1);

        System.out.println(properties);

        return registrationBean;
    }


    public class MyFilter implements Filter {
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) srequest;
            System.out.println("this is MyFilter,url :" + request.getRequestURI());
            filterChain.doFilter(srequest, sresponse);
        }

        public void destroy() {

        }
    }
}
