package com.thandb.tutorial.hibernate.chapter01.test;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.thandb.tutorial.hibernate.chapter01.domain.Event;
import com.thandb.tutorial.hibernate.chapter01.domain.Person;
import com.thandb.tutorial.hibernate.chapter01.util.HibernateUtil;

public class EventManager {

	public static void main(String[] args) {
		EventManager mgr = new EventManager();

//		if (args[0].equals("store")) {
//			mgr.createAndStoreEvent("My Event", new Date());
//		} else if (args[0].equals("list")) {
		List<Event> events = mgr.listEvents();
			for (int i = 0; i < events.size(); i++) {
				Event theEvent = (Event) events.get(i);
				System.out.println("Event: " + theEvent.getTitle() + " Time: "
						+ theEvent.getDate());
				
				Set<Person> persons = theEvent.getPersons();
				for (Person person : persons) {
					System.out.println(person);
				}
				
				System.out.println("------------------------------------------");
			}
//		}
			
//			Long eventId = mgr.createAndStoreEvent("My Event", new Date());
//            Long personId = mgr.createAndStorePerson("Foo1", "Bar1");
//            mgr.addPersonToEvent(personId, eventId);
//            personId = mgr.createAndStorePerson("Foo2", "Bar2");
//            mgr.addPersonToEvent(personId, eventId);
//            personId = mgr.createAndStorePerson("Foo3", "Bar3");
//            mgr.addPersonToEvent(personId, eventId);
//            personId = mgr.createAndStorePerson("Foo4", "Bar4");
//            mgr.addPersonToEvent(personId, eventId);
//            
//            mgr.addEmailToPerson(personId, "email1@gmail.com");
//            mgr.addEmailToPerson(personId, "email2@gmail.com");

		HibernateUtil.getSessionFactory().close();
	}

	private Long createAndStoreEvent(String title, Date theDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Event theEvent = new Event();
		theEvent.setTitle(title);
		theEvent.setDate(theDate);
		Long id = (Long) session.save(theEvent);

		session.getTransaction().commit();
		
		return id;
	}

	/*
	 * private List<Event> getAllEvents() { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession();
	 * session.beginTransaction();
	 * 
	 * return session.createCriteria(Event.class).list(); }
	 */

	private List<Event> listEvents() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Event> result = session.createQuery("from Event").list();
		for (Event event : result) {
			Hibernate.initialize(event.getPersons());
		}
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
	
	private Long createAndStorePerson(String firstName, String lastName){
		Long id = 0l;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Person thePerson = new Person();
		thePerson.setFirstName(firstName);
		thePerson.setLastName(lastName);
		id = (Long) session.save(thePerson);

		session.getTransaction().commit();
		
		return id;
	}
	
	private void addEmailToPerson(Long personId, String emailAddress) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Person aPerson = (Person) session.load(Person.class, personId);
		// adding to the emailAddress collection might trigger a lazy load of the collection
		aPerson.getEmailAddresses().add(emailAddress);
		
		session.getTransaction().commit();
	}
}