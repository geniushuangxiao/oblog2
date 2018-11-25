package cn.techtopic.oblogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OblogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OblogsApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder encrypt() {
		return new BCryptPasswordEncoder();
	}
}
