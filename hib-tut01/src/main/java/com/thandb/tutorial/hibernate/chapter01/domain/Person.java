package com.thandb.tutorial.hibernate.chapter01.domain;

import java.util.HashSet;
import java.util.Set;

public class Person {
	private Long id;
	private int age;
	private String firstName;
	private String lastName;
	private Set<String> emailAddresses = new HashSet<String>();

	private Set<Event> events = new HashSet<Event>();

	public Set<Event> getEvents() {
		return events;
	}
	 	public void addToEvent(Event event) {
		this.getEvents().add(event);
		event.getParticipants().add(this);
	}
	
	public void removeFromEvent(Event event) {
		this.getEvents().remove(event);
		event.getParticipants().remove(this);
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<String> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(Set<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
	
	public void addEmailToPerson(String email) {
		this.getEmailAddresses().add(email);
	}
	
	public void removeEmailFromPerson(String email) {
		this.getEmailAddresses().remove(email);
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName() + " is " + getAge()
				+ " years old." + " Contacts by emails: " + getEmailAddresses().toString().substring(1, getEmailAddresses().toString().length() - 1);
	}

}
