package com.example.formlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class FormloginApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FormloginApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FormloginApplication.class);
    }

	@Bean
	public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeRequests(authorize -> authorize.anyRequest().authenticated())
				.formLogin().and()
				.build();
	}

	@GetMapping("/hello")
	public String hello(@AuthenticationPrincipal User user) {
		return "Hello " + user.getUsername() + "!";
	}

}
