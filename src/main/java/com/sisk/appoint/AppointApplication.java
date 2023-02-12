package com.sisk.appoint;

import com.sisk.appoint.entity.Role;
import com.sisk.appoint.entity.RoleType;
import com.sisk.appoint.service.AppointUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class AppointApplication {


	public static void main(String[] args) {
		SpringApplication.run(AppointApplication.class, args);

	}

	@Bean
	public CommandLineRunner runner(AppointUserService userService){
		return args -> {
			Role role = new Role(RoleType.USER);
			userService.saveRole(role);

		};
	}
}
