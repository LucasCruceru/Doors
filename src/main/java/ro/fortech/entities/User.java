package ro.fortech.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    private String password;
    private String username;


    //getters
    public String getPassword() {return password;}
    public Long getId() {return id;}
    public String getUsername() {return username;}

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    User(){}
}
