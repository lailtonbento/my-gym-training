package br.com.mygymtraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"br.com.mygymtraining"})
public class MyGymTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyGymTrainingApplication.class, args);
    }

}
