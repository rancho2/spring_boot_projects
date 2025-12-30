package com.spring.ollama;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.ollama.entities.Train;
import com.spring.ollama.entities.User;
import com.spring.ollama.services.UserBookingService;
import com.spring.ollama.util.UserServiceUtil;

@SpringBootApplication
public class JavaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
		System.out.println("Running train booking app");
		Scanner scn=new Scanner(System.in);
		int option=0;
		UserBookingService userBookingService;
		try {
			userBookingService=new UserBookingService();
		}catch(IOException ex)
		{
			System.out.println("There is something wrong");
			return;
		}
		while(option!=7)
		{
			System.out.println("Choose option");
			System.out.println("1. Sign up");
			System.out.println("2. Login");
			System.out.println("3. Fetch Bookings");
			System.out.println("4. Search Trains");
			System.out.println("5. Book a seat");
			System.out.println("6. Cancel my booking");
			System.out.println("7. Exit the app");
			option=scn.nextInt();
			switch(option)
			{
			case 1:
				System.out.println("Enter the username to signup");
				String nameToSignUp=scn.next();
				System.out.println("Enter the password to signup");
				String passwordToSignUp=scn.next();
				User user=new User(UUID.randomUUID().toString(),nameToSignUp,passwordToSignUp,
						UserServiceUtil.hashPassword(passwordToSignUp),
						new ArrayList<>());
				userBookingService.signup(user);
				break;
			case 2:
				System.out.println("Enter the username to login");
				String nameToLogin=scn.next();
				System.out.println("Enter the password to login");
				String passwordToLogin=scn.next();
				User userToLogin=new User(UUID.randomUUID().toString(),nameToLogin,passwordToLogin,
						UserServiceUtil.hashPassword(passwordToLogin),
						new ArrayList<>()); 
				try {
					userBookingService=new UserBookingService(userToLogin);
				}catch(IOException ex)
				{
					return;
				}
				break;
			case 3:
				System.out.println("Fetch your bookings");
				userBookingService.fetchBooking();
				break;
			case 4:
				System.out.println("Type your source station");
				String source=scn.next();
				System.out.println("Type your destination station");
				String destination=scn.next();
				List<Train> trains=userBookingService.getTrains(source,destination);
				int index=1;
				for(Train t:trains)
				{
					System.out.println(index+" Train id : "+t.getTrainId());
					for(Map.Entry<String, String> entry:t.getStationTimes().entrySet())
					{
						System.out.println("station "+entry.getKey()+" time: "+entry.getValue());
					}
				}
				System.out.println("Select a train by typing 1,2,3...");
				Train train = trains.get(scn.nextInt());
				break;
			}
		}
	}

}
