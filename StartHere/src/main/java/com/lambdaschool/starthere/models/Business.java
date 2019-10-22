package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.starthere.logging.Loggable;
import com.lambdaschool.starthere.services.UserService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Loggable
@Entity
@Table(name = "businesses")
public class Business
{
    @Id
    @Column(name = "userid")
    private long userid;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;


    @OneToMany(mappedBy = "business",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Pickup> businesspickups = new ArrayList<>();

    public Business()
    {
    }

    public Business( List<Pickup> businesspickups, User user)
    {
        this.businesspickups = businesspickups;
        this.user = user;
    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public List<Pickup> getBusinesspickups()
    {
        return businesspickups;
    }

    public void setBusinesspickups(List<Pickup> businesspickups)
    {
        this.businesspickups = businesspickups;
    }
}
