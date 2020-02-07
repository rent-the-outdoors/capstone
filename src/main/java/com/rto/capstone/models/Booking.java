package com.rto.capstone.models;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private long id;

    @Column(nullable = false, name = "date", columnDefinition = "DATE")
    private String date;

    @Column(nullable = false, name = "address", columnDefinition = "VARCHAR(500)")
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    public Booking(long id, String date, String address) {
        this.id = id;
        this.date = date;
        this.address = address;
    }

    public Booking(String date, String address) {
        this.date = date;
        this.address = address;
    }


    public Booking(){}
}
