package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.starthere.logging.Loggable;

import javax.persistence.*;
import java.util.Date;

@Loggable
@Entity
@Table(name = "pickups")
public class Pickup extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pickupid;

    private String foodtype;
    private int quantity;
    private String quantityunit;
    private Date postdate;
    private String deliveryaddress;
    private String deliverycity;
    private String deliverystate;
    private String zip;
    private String website;

    @ManyToOne
    @JoinColumn(name = "volunteerid",
            nullable = false)
    @JsonIgnoreProperties("volunteerpickups")
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name = "businessid",
            nullable = false)
    @JsonIgnoreProperties("businesspickups")
    private Business business;

    public Pickup()
    {
    }

    public Pickup(String foodtype, int quantity, String quantityunit, Date postdate, String deliveryaddress, String deliverycity, String deliverystate, String zip, String website, Volunteer volunteer, Business business)
    {
        this.foodtype = foodtype;
        this.quantity = quantity;
        this.quantityunit = quantityunit;
        this.postdate = postdate;
        this.deliveryaddress = deliveryaddress;
        this.deliverycity = deliverycity;
        this.deliverystate = deliverystate;
        this.zip = zip;
        this.website = website;
        this.volunteer = volunteer;
        this.business = business;
    }

    public long getPickupid()
    {
        return pickupid;
    }

    public void setPickupid(long pickupid)
    {
        this.pickupid = pickupid;
    }

    public String getFoodtype()
    {
        return foodtype;
    }

    public void setFoodtype(String foodtype)
    {
        this.foodtype = foodtype;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public String getQuantityunit()
    {
        return quantityunit;
    }

    public void setQuantityunit(String quantityunit)
    {
        this.quantityunit = quantityunit;
    }

    public Date getPostdate()
    {
        return postdate;
    }

    public void setPostdate(Date postdate)
    {
        this.postdate = postdate;
    }

    public String getDeliveryaddress()
    {
        return deliveryaddress;
    }

    public void setDeliveryaddress(String deliveryaddress)
    {
        this.deliveryaddress = deliveryaddress;
    }

    public String getDeliverycity()
    {
        return deliverycity;
    }

    public void setDeliverycity(String deliverycity)
    {
        this.deliverycity = deliverycity;
    }

    public String getDeliverystate()
    {
        return deliverystate;
    }

    public void setDeliverystate(String deliverystate)
    {
        this.deliverystate = deliverystate;
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

    public Volunteer getVolunteer()
    {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer)
    {
        this.volunteer = volunteer;
    }

    public Business getBusiness()
    {
        return business;
    }

    public void setBusiness(Business business)
    {
        this.business = business;
    }
}
