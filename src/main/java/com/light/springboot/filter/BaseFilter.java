package com.light.springboot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@WebFilter(urlPatterns = "/",filterName = "BaseFileter")
public class BaseFilter implements Filter{

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// do nothing
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = ((HttpServletRequest) request).getServletPath();
        logger.info("login forwarded: " + url);
        if (!StringUtils.isEmpty(url)) {
            if (url.indexOf(".html") > 0 || url.indexOf(".css") > 0 || url.indexOf(".js") >0) {
            	filterChain.doFilter(request,response);
            }    	
        }

        if ("/login".equals(url)) {
        	request.getRequestDispatcher("/login").forward(request, response);
        } else {
            filterChain.doFilter(request,response);
        }
	}

	@Override
	public void destroy() {
		// do nothing.
	}

}
