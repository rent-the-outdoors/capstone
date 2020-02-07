package com.rto.capstone.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Image> images;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Place> places;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Booking> bookings;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    public long id;

    @Column(nullable = false, name = "first_name", columnDefinition = "VARCHAR(255)")
    private String first_name;

    @Column(nullable = false, name = "last_name", columnDefinition = "VARCHAR(255)")
    private String last_name;

    @Column(nullable = false, name = "email", columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(nullable = false, name = "username", columnDefinition = "VARCHAR(25)")
    private String username;

    @Column(nullable = false, name = "password", columnDefinition = "VARCHAR(100)")
    private String password;


    @Column(nullable = false, name = "phone_num", columnDefinition = "VARCHAR(12)")
    private String phone_num;

    @Column(name = "image_path", columnDefinition = "VARCHAR(2050)")
    private String image_path;

    public User(long id,
                String first_name,
                String last_name,
                String email,
                String username,
                String password,
                String phone_num,
                String image_path)
    {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone_num = phone_num;
        this.image_path = image_path;
    }

    public User(String first_name,
                String last_name,
                String email,
                String username,
                String password,
                String phone_num,
                String image_path)
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone_num = phone_num;
        this.image_path = image_path;
    }

    public User(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
