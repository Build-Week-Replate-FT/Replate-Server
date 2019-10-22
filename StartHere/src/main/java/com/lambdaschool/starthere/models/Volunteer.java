package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lambdaschool.starthere.logging.Loggable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;


@Loggable
@Entity
@Table(name = "volunteers")
public class Volunteer extends Auditable
{
    @Id
    private long volunteerid;

    @OneToMany(mappedBy = "volunteer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Pickup> volunteerpickups = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "volunteerid")
    @JsonIgnore
    User user;

    public Volunteer()
    {
    }

    public Volunteer(long volunteerid, List<Pickup> volunteerpickups)
    {
        this.volunteerid = volunteerid;
        this.volunteerpickups = volunteerpickups;
    }

    public long getVolunteerid()
    {
        return volunteerid;
    }

    public void setVolunteerid(long volunteerid)
    {
        this.volunteerid = volunteerid;
    }

    public List<Pickup> getVolunteerpickups()
    {
        return volunteerpickups;
    }

    public void setVolunteerpickups(List<Pickup> volunteerpickups)
    {
        this.volunteerpickups = volunteerpickups;
    }
}
