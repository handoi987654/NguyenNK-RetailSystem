package com.Desert.Repository;

import com.Desert.Entity.User;

public interface UserRepo {

    User getUser(String username, String password);

    User getUser(String username);
}
