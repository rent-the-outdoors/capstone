package com.rto.capstone.models;

import javax.persistence.*;

@Entity
@Table(name="activities")
public class Activity {

    public Activity() {

    }
    public Activity(Long id, String activity) {
        this.id = id;
        this.activity = activity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String activity;


    @ManyToOne
    @JoinColumn(name="place_id")
    private Place place;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
