package dev.sn.service;

import dev.sn.entities.User1;
import dev.sn.repositories.User1Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final User1Repository user1Repository;

    public UserService(User1Repository user1Repository) {
        this.user1Repository = user1Repository;
    }

    public List<User1> allUsers() {
        List<User1> users = new ArrayList<>();

        user1Repository.findAll().forEach(users::add);

        return users;
    }
}
