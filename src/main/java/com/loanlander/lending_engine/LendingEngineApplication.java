package com.loanlander.lending_engine;

import com.loanlander.lending_engine.domain.model.User;
import com.loanlander.lending_engine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LendingEngineApplication implements CommandLineRunner {
    @Autowired
    private  UserRepository userRepository ;

    public static void main(String[] args) {
        SpringApplication.run(LendingEngineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("jhon","Jhon","Vlac",25,"Software Dev"));
        userRepository.save(new User("Abdou","Abdou","Med",23,"Senior Dev"));
        userRepository.save(new User("Bill","Bill","Owen",31,"Architect"));
    }
}
