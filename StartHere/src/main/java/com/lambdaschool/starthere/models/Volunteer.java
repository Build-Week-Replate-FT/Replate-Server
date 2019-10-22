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
    @Column(name = "userid")
    private long userid;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "volunteer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Pickup> volunteerpickups = new ArrayList<>();

    public Volunteer()
    {
    }

    public Volunteer(User user, List<Pickup> volunteerpickups)
    {
        this.user = user;
        this.volunteerpickups = volunteerpickups;
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

    public List<Pickup> getVolunteerpickups()
    {
        return volunteerpickups;
    }

    public void setVolunteerpickups(List<Pickup> volunteerpickups)
    {
        this.volunteerpickups = volunteerpickups;
    }
}
