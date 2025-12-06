package com.moses.s3_upload_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amigoscode.customer.Customer;
import com.amigoscode.customer.CustomerRepository;
import com.amigoscode.customer.Gender;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class S3UploadApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3UploadApiApplication.class, args);
	}

    @Bean
    CommandLineRunner runner(
            CustomerRepository customerRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            var faker = new Faker();
            Random random = new Random();
            Name name = faker.name();
            String firstName = name.firstName();
            String lastName = name.lastName();
            int age = random.nextInt(16, 99);
            Gender gender = age % 2 == 0 ? Gender.MALE : Gender.FEMALE;
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@amigoscode.com";
            Customer customer = new Customer(
                    firstName +  " " + lastName,
                    email,
                    passwordEncoder.encode("password"),
                    age,
                    gender);
            customerRepository.save(customer);
            System.out.println(email);
        };
    }
}
