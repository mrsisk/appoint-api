package com.sisk.appoint;

import com.sisk.appoint.datetime.DateFactory;
import com.sisk.appoint.datetime.TimeFactory;
import com.sisk.appoint.entity.Role;
import com.sisk.appoint.entity.RoleType;
import com.sisk.appoint.service.AppointUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication()
public class AppointApplication {


	public static void main(String[] args) {
		SpringApplication.run(AppointApplication.class, args);

	}

	@Bean
	public CommandLineRunner runner(AppointUserService userService, DateFactory factory){
		return args -> {
			Role role = new Role(RoleType.USER);
			userService.saveRole(role);
			System.out.println("helllo");
			System.out.println(factory.workingDays(2));
		};
	}
}
