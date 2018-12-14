package com.app.service;

import com.app.model.User;
import com.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... strings){
        User user1 = new User("Ashan",20000);
        User user2 = new User("Heran",50000);
        User user3 = new User("John",93000);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
