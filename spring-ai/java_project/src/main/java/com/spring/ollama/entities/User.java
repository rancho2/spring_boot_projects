package com.spring.ollama.entities;

import java.util.List;

public class User {
	private String userId;
	private String name;
	private String password;
	private String hashedPassword;
	private List<Ticket> tickets;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	public User(String userId, String name, String password, String hashedPassword, List<Ticket> tickets) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.hashedPassword = hashedPassword;
		this.tickets = tickets;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void printTickets()
	{
		for(int i=0;i<tickets.size();i++)
		{
			System.out.println(tickets.get(i).getTrain().getTrainInfo());
		}
	}
}
