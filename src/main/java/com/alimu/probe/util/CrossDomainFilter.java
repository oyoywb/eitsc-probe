package com.alimu.probe.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
  *  解决跨域
 * @author ymsn
 * @date  2019年10月18日
 */
public class CrossDomainFilter implements Filter {


    public void init(FilterConfig arg0) throws ServletException {
        WebApplicationContext webApplicationContext = (WebApplicationContext) ContextLoader.getCurrentWebApplicationContext();
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 指定允许其他域名访问
        response.addHeader("Access-Control-Allow-Origin", "*");
     	// 响应类型
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
     	// 响应头设置
        response.addHeader("Access-Control-Allow-Headers","Content-Type,X-Requested-With,accept,"
        		+ "Origin,Access-Control-Request-Method,Access-Control-Request-Headers,token");
     	if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpStatus.SC_NO_CONTENT);
		}
     	
        filterChain.doFilter(req, resp);
    }
    
    public void destroy() {
    	
    }


}
