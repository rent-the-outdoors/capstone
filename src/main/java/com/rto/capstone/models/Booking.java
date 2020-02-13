package com.rto.capstone.models;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private long id;
//comment
    @Column(nullable = false, name = "dateStart", columnDefinition = "DATE")
    private String dateStart;

    @Column(nullable = false, name = "dateEnd", columnDefinition = "DATE")
    private String dateEnd;

    @Column(nullable = false, name = "address", columnDefinition = "VARCHAR(500)")
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    public Booking(long id, String dateStart, String dateEnd, String address) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.address = address;
    }

    public Booking(String dateStart, String address, String dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.address = address;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Booking(){}
}
