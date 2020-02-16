package com.rto.capstone.models;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class PlaceImage {

    public PlaceImage() {}

    public PlaceImage(Long id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    @ManyToOne
    @JoinColumn(name="place_id")
    private Place place;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
