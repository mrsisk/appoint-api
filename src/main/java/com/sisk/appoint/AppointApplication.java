package com.sisk.appoint;

import com.sisk.appoint.datetime.DateFactory;
import com.sisk.appoint.datetime.TimeFactory;
import com.sisk.appoint.entity.Role;
import com.sisk.appoint.entity.RoleType;
import com.sisk.appoint.model.RegisterRequest;
import com.sisk.appoint.service.AppointUserService;
import com.sisk.appoint.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@SpringBootApplication()
public class AppointApplication {


	public static void main(String[] args) {
		SpringApplication.run(AppointApplication.class, args);

	}

	@Bean
	public CommandLineRunner runner(AppointUserService userService, DateFactory factory, AuthenticationService authenticationService){
		return args -> {
			Role role = new Role(RoleType.USER);
			userService.saveRole(role);
			Role role2 = new Role(RoleType.ADMIN);
			userService.saveRole(role2);

			authenticationService.register(new RegisterRequest("admin@gmail.com", "6000@Feetz"), RoleType.ADMIN);

			System.out.println(ZonedDateTime.now());
			System.out.println(factory.workingDays(2));
		};
	}
}
