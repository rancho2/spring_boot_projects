package com.spring.ollama.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.ollama.entities.Ticket;
import com.spring.ollama.entities.Train;
import com.spring.ollama.entities.User;
import com.spring.ollama.util.UserServiceUtil;

public class UserBookingService {
	private User user;
	private static final String USERS_PATH = "src/main/java/com/spring/ollama/localdb/users.json";
	private ObjectMapper objectMapper=new ObjectMapper();
	private List<User> userList=new ArrayList<>();
	
	public UserBookingService() throws StreamReadException, DatabindException, IOException {
		super();
		loadUsers();
	}
	public UserBookingService(User user) throws StreamReadException, DatabindException, IOException {
		this.user = user;
		loadUsers();
	}
	public List<User> loadUsers() throws StreamReadException, DatabindException, IOException
	{
		File users=new File(USERS_PATH);
		return objectMapper.readValue(users, new TypeReference<List<User>>() {});	
		
	}
	public boolean loginUser()
	{
		Optional<User> foundUser=userList.stream().filter(user1 -> {
			return user1.getName().equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),
					user1.getHashedPassword());
		}).findFirst();
		return foundUser.isPresent();
	}
	public boolean signup(User user1)
	{
		try {
			userList.add(user1);
			saveUserListToFile();
			return true;
		}catch(IOException ex)
		{
			return false;
		}
	}
	private void saveUserListToFile() throws StreamWriteException, DatabindException, IOException
	{
		File usersFile=new File(USERS_PATH);
		objectMapper.writeValue(usersFile, userList);
	}
	public void fetchBooking()
	{
		user.printTickets();
	}
	public boolean cancelBooking(String ticketId) throws StreamWriteException, DatabindException, IOException
	{
		Optional<Ticket> tickets1=user.getTickets().stream().filter(ticket->ticket.getTicketId().equals(ticketId)).findFirst();
		if(tickets1.isPresent())
		{
			user.getTickets().remove(tickets1.get());
			updateUserInList();
			File userFile=new File(USERS_PATH);
			objectMapper.writeValue(userFile, userList);
			return true;
		}
		else
		{
			return false;
		}
	}
	private void updateUserInList()
	{
		for(int i=0;i<userList.size();i++)
		{
			if(userList.get(i).getName().equals(user.getName()))
			{
				userList.set(i, user);
				break;
			}
		}
	}
	public List<Train> getTrains(String source,String destination)
	{
		try {
			TrainService trainService=new TrainService();
			return trainService.searchTrains(source, destination);
		}catch(IOException ex)
		{
			return null;
		}
	}
}
