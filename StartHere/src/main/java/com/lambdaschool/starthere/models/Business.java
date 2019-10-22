package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.starthere.logging.Loggable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Loggable
@Entity
@Table(name = "businesses")
public class Business
{
    @Id
    private long businessid;

    @OneToMany(mappedBy = "business",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Pickup> businesspickups = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "businessid")
    @JsonIgnore
    User user;

    public Business()
    {
    }

    public Business( List<Pickup> businesspickups, User user)
    {
        this.businesspickups = businesspickups;
        this.user = user;
    }

    public long getBusinessid()
    {
        return businessid;
    }

    public void setBusinessid(long businessid)
    {
        this.businessid = businessid;
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
