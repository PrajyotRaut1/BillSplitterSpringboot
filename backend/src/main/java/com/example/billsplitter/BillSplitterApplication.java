package com.example.billsplitter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class BillSplitterApplication {
public static void main(String[] args) {
SpringApplication.run(BillSplitterApplication.class, args);

System.out.println("Bill Splitter Application Started");
}


@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}
}