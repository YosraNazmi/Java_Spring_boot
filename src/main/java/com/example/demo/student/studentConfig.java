package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class studentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            studentRepository repository
    ){
        return args ->{
                   Student mariam = new Student(
                            "mariam",
                            "mariam@gmail.com",
                            LocalDate.of(1997, Month.AUGUST,5)
                    );

            Student Yosra = new Student(
                    "Yosra",
                    "yosra@gmail.com",
                    LocalDate.of(2000, Month.AUGUST,5)
            );

            repository.saveAll(
                 List.of(mariam,Yosra)
            );

        };
    }
}
