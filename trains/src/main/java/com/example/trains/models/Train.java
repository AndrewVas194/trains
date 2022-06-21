package com.example.trains.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String trainsName;
    private String sendfrom;
    private String tosend;
    private String time_to_send;
    private Integer count_site_places;
    private String price;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTrainsName() { return trainsName; }
    public void setTrainsName(String trainsName) { this.trainsName = trainsName; }

    public String getSendfrom() { return sendfrom; }
    public void setSendfrom(String sendfrom) { this.sendfrom = sendfrom; }

    public String getTosend() { return tosend; }
    public void setTosend(String tosend) { this.tosend = tosend; }

    public String getTime_to_send() { return time_to_send; }
    public void setTime_to_send(String time_to_send) { this.time_to_send = time_to_send; }

    public Integer getCount_site_places() { return count_site_places; }
    public void setCount_site_places(Integer count_site_places) { this.count_site_places = count_site_places; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }


    public Train() {
    }

    public Train(String trainsName, String sendfrom, String tosend,
                 String time_to_send, Integer count_site_places, String price) {

        this.trainsName = trainsName;
        this.sendfrom = sendfrom;
        this.tosend = tosend;
        this.time_to_send = time_to_send;
        this.count_site_places = count_site_places;
        this.price = price;
    }
}