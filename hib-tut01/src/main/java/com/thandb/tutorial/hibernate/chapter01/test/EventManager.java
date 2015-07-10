package com.thandb.tutorial.hibernate.chapter01.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.thandb.tutorial.hibernate.chapter01.domain.Event;
import com.thandb.tutorial.hibernate.chapter01.domain.Person;
import com.thandb.tutorial.hibernate.chapter01.util.HibernateUtil;

public class EventManager {

	public static void main(String[] args) {
		EventManager mgr = new EventManager();

		if (args[0].equals("store")) {
			mgr.createAndStoreEvent("My Event", new Date());
		} else if (args[0].equals("list")) {
			List events = mgr.listEvents();
			for (int i = 0; i < events.size(); i++) {
				Event theEvent = (Event) events.get(i);
				System.out.println("Event: " + theEvent.getTitle() + " Time: "
						+ theEvent.getDate());
			}
		}

		HibernateUtil.getSessionFactory().close();
	}

	private void createAndStoreEvent(String title, Date theDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Event theEvent = new Event();
		theEvent.setTitle(title);
		theEvent.setDate(theDate);
		session.save(theEvent);

		session.getTransaction().commit();
	}

	/*
	 * private List<Event> getAllEvents() { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction();
	 * 
	 * return session.createCriteria(Event.class).list(); }
	 */

	private List listEvents() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from Event").list();
		session.getTransaction().commit();
		return result;
	}

	private void addPersonToEvent(Long personId, Long eventId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Person aPerson = (Person) session.load(Person.class, personId);
		Event anEvent = (Event) session.load(Event.class, eventId);
		aPerson.getEvents().add(anEvent);

		session.getTransaction().commit();
	}
}