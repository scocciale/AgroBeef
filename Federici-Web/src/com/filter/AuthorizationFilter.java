package com.filter;

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

import com.managedBean.UserMB;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "/pages/*" })
public class AuthorizationFilter implements Filter {

	private final String NOT_LOGGED = "loginfilter_not_logged";
	private final String LOGIN_FILTER_MSG = "LOGIN_FILTER_MSG";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		try {
			HttpSession ses = req.getSession(true);
			UserMB user = null;

			user = (UserMB) ses.getAttribute("userMB");

			if (user != null && user.getUtente() != null) {
				req.getSession().setAttribute("utenteCod", user.getUtente().getUteId());
				chain.doFilter(request, response);
			} else {
				req.getSession().setAttribute(LOGIN_FILTER_MSG, NOT_LOGGED);
				((HttpServletResponse) response).sendRedirect("../login.xhtml");

			}
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute(LOGIN_FILTER_MSG, NOT_LOGGED);
			((HttpServletResponse) response).sendRedirect("../login.xhtml");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
