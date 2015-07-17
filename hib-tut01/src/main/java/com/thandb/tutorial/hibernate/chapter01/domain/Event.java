package com.thandb.tutorial.hibernate.chapter01.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Event {
	private long id;
	private String title;
	private Date date;

	private Set<Person> persons = new HashSet<Person>();

	private Set<Person> participants = new HashSet<Person>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public Set<Person> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Person> participants) {
		this.participants = participants;
	}

	@Override
	public String toString() {
		return getId() + " - " + getTitle() + " - " + getDate().toString();
	}

}
