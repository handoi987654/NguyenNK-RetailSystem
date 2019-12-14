package com.Desert.Service;

import com.Desert.Repository.UserRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthenticationServiceBean implements AuthenticationService {

    private final UserRepo userRepo;

    public AuthenticationServiceBean(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean login(String username, String password) {
        return userRepo.getUser(username, password) != null;
    }
}
