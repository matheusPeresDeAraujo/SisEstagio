/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.filter;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author knupp
 */
@WebFilter(servletNames = { "Faces Servlet" })
public class AuthenticatorFilter implements javax.servlet.Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String vUrl1 = "";
		String vUrl2 = "";
		String vUrl3 = "";
		String vUrl4 = "";
		
		vUrl1 = req.getContextPath() + "/index.xhtml";
		vUrl2 = req.getContextPath() + "/resultado.xhtml";
		vUrl3 = req.getContextPath() + "/admin/admin.xhtml";
		vUrl4 = req.getContextPath() + "/esqueciMinhaSenha.xhtml";
		
		//You need to let the filter skip JSF resource requests on CSS/JS/image files.
		if (req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) {
		    chain.doFilter(request, response);
		    return;
		}
				

		if (req.getRequestURI().endsWith(vUrl1)) {
			chain.doFilter(request, response);
		} else if (req.getRequestURI().endsWith(vUrl2)) {
			chain.doFilter(request, response);
		} else if (req.getRequestURI().endsWith(vUrl3)) {
			chain.doFilter(request, response);
		} else if (req.getRequestURI().endsWith(vUrl4)) {
			chain.doFilter(request, response);
		} else if (req.getRequestURI().endsWith(req.getContextPath() + "/")) {
			chain.doFilter(request, response);	
		} else if (verificarSession(req) == null) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(req.getContextPath() + "/admin/admin.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

	public Integer verificarSession(HttpServletRequest request)
			throws ServletException {
		Integer idUsuario = null;
		try {
			idUsuario = (Integer) request.getSession()
					.getAttribute("idUsuario");
		} catch (Exception e) {
			idUsuario = null;
		}
		return idUsuario;
	}
}