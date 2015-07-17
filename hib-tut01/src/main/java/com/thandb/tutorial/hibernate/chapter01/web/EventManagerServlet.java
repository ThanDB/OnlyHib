package com.thandb.tutorial.hibernate.chapter01.web;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thandb.tutorial.hibernate.chapter01.util.HibernateUtil;

public class EventManagerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3657714689162410090L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		
		try {
			// Begin unit of work
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			
			// Process request and render page ...
			
			// End unit of work
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			if (ServletException.class.isInstance(e)) {
				throw (ServletException) e;
			} else {
				throw new ServletException(e);
			}
		}
	}

}
