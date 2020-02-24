package com.rto.capstone.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String activities;

    @Column
    private String amenities;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String cost_per_day;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public List<PlaceImage> getPlaceImages() {
        return placeImages;
    }

    public void setPlaceImages(List<PlaceImage> placeImages) {
        this.placeImages = placeImages;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceImage> placeImages;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Activity> Activities;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public Place(Long id,
                 String title,
                 String address,
                 String cost_per_day,
                 String description,
                 String amenities,
                 String activities){
        this.id = id;
        this.title = title;
        this.address = address;
        this.cost_per_day = cost_per_day;
        this.description = description;
        this.amenities = amenities;
        this.activities = activities;

    }

    public Place(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCost_per_day() { return cost_per_day; }

    public void setCost_per_day(String cost_per_day) { this.cost_per_day = cost_per_day; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
