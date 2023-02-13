package com.base.hitoindividualdwes.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(name="Users.findAll", query="Select U FROM Users U")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "username")
    private String username;
    @OneToMany(mappedBy="username")
    private List<Tema> temas;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;


   public Users(){

   }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }


}
