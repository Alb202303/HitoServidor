package com.base.hitoindividualdwes.model;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class Login {

    private Users username;

    public Login() {
        this.username = null;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    public void destroyLogin(){this.username= null;}
}
