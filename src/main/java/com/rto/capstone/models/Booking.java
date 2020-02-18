package com.rto.capstone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private long id;
//comment
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEnd;

    @Column(nullable = false, name = "address", columnDefinition = "VARCHAR(500)")
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    @JsonIgnore
    private Place place;

    public Booking(long id, Date dateStart, Date dateEnd, String address) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.address = address;
    }

    public Booking(Date dateStart, String address, Date dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.address = address;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
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
