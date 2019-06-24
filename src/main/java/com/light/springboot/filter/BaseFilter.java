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
import javax.servlet.http.HttpSession;
import com.light.springboot.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@WebFilter(urlPatterns = "/",filterName = "BaseFileter")
public class BaseFilter implements Filter{

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(BaseFilter.class);
	
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
            if (url.indexOf(".html") > 0 || url.indexOf(".css") > 0 || url.indexOf(".js") >0 || isSwaggerUrl(url)) {
            	filterChain.doFilter(request,response);
				return;
            }    	
        }

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

        if (!"/login".equals(url) && user == null) {
			response.sendRedirect("/login");
        }  else {
            filterChain.doFilter(request,response);
        }
	}

	@Override
	public void destroy() {
		// do nothing.
	}

	private boolean isSwaggerUrl(String url) {
		if(url.indexOf("swagger")!=-1||url.indexOf("api-docs")!=-1){
			return true;
		}
		return false;
	}
}
