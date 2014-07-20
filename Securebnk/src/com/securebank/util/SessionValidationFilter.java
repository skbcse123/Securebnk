package com.securebank.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SessionValidationFilter
 */
public class SessionValidationFilter implements Filter {

    private List<String> list;
    public SessionValidationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		//System.out.println(list.contains(httpServletRequest.getServletPath()));

		if(list.contains(httpServletRequest.getServletPath())){
			if(httpServletRequest.getSession(false) != null){
				//System.out.println(httpServletRequest.getSession(false) == null);
				if(httpServletRequest.getSession(false).getAttribute("USERLOGINID") == null){
					httpServletResponse.sendRedirect("../common/login.faces");	
					httpServletResponse.flushBuffer();					
				}else{
					chain.doFilter(request, response);
				}
			}else{
				httpServletResponse.sendRedirect("../common/login.faces");
			}
		}else{
		chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		list = new ArrayList<String>(0);
		
		//consultent trainer url
		list.add("/xhtml/consultenttrainer/consultenttrainer.faces");
		
		
	}

}
