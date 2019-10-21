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

// User is considered the parent entity

@Loggable
@Entity
@Table(name = "users")
public class User extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    private String userType;

    @Email
    @Column(nullable = false,
            unique = true)
    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user",
               cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userroles = new ArrayList<>();

    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String website;

    @OneToMany(mappedBy = "volunteer",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Pickup> volunteerpickups = new ArrayList<>();

    @OneToMany(mappedBy = "business",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Pickup> businesspickups = new ArrayList<>();

    public User()
    {
    }

    public User(String userType, @Email String email, String password, List<UserRoles> userroles, String name, String address, String city, String state, String zip, String website, List<Pickup> volunteerpickups, List<Pickup> businesspickups)
    {
        this.userType = userType;
        setEmail(email);
        setPassword(password);
        for (UserRoles ur : userroles)
        {
            ur.setUser(this);
        }
        this.userroles = userroles;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.website = website;
        this.volunteerpickups=volunteerpickups;
        this.businesspickups = businesspickups;
    }

    public User(String username,
                String password,
                String primaryemail,
                List<UserRoles> userRoles)
    {


    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getEmail()
    {
        if (email == null) // this is possible when updating a user
        {
            return null;
        } else
        {
            return email.toLowerCase();
        }
    }

    public void setEmail(String email)
    {
        this.email = email.toLowerCase();
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public List<UserRoles> getUserroles()
    {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles)
    {
        this.userroles = userroles;
    }

    public List<Pickup> getVolunteerpickups()
    {
        return volunteerpickups;
    }

    public void setVolunteerpickups(List<Pickup> volunteerpickups)
    {
        this.volunteerpickups = volunteerpickups;
    }

    public List<Pickup> getBusinesspickups()
    {
        return businesspickups;
    }

    public void setBusinesspickups(List<Pickup> businesspickups)
    {
        this.businesspickups = businesspickups;
    }

    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userroles)
        {
            String myRole = "ROLE_" + r.getRole()
                                       .getName()
                                       .toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        state = state;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userid=" + userid +
                ", userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userroles=" + userroles +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", website='" + website + '\'' +
                ", volunteerpickups=" + volunteerpickups +
                ", businesspickups=" + businesspickups +
                '}';
    }
}