package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.mytaxi.domainvalue.CarType;

@Entity
@Table(
    name = "Car",
    uniqueConstraints = @UniqueConstraint(name = "licensePlate", columnNames = {"LICENSE_PLATE"}))
public class CarDO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_ID")
    private Long carId;

    @Column(name = "DATE_REGISTERED")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateRegistered = ZonedDateTime.now();

    @Column(nullable = false, name = "LICENSE_PLATE")
    @NotNull(message = "License can not be null!")
    private String licensePlate;

    @Column(nullable = false, name = "SEAT_COUNT")
    private Long seatCount;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "CAR_TYPE")
    private CarType carType;

    @Column(name = "MANUF_ID")
    private String manufacturer;

    @Column(name = "DELETED")
    private boolean isDeleted;


    public CarDO()
    {}


    public CarDO(String licensePlate, Long seatCount, CarType carType, String manufacturer, Integer rating)
    {
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.rating = rating;
        this.carType = carType;
        this.dateRegistered = ZonedDateTime.now();
        this.manufacturer = manufacturer;
    }


    public Long getCarId()
    {
        return carId;
    }


    public CarType getCarType()
    {
        return carType;
    }


    public void setCarType(CarType carType)
    {
        this.carType = carType;
    }


    public void setCarId(Long carId)
    {
        this.carId = carId;
    }


    public ZonedDateTime getDateRegistered()
    {
        return dateRegistered;
    }


    public void setDateRegistered(ZonedDateTime dateRegistered)
    {
        this.dateRegistered = dateRegistered;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }


    public Long getSeatCount()
    {
        return seatCount;
    }


    public void setSeatCount(Long seatCount)
    {
        this.seatCount = seatCount;
    }


    public Integer getRating()
    {
        return rating;
    }


    public void setRating(Integer rating)
    {
        this.rating = rating;

    }


    public boolean isDeleted()
    {
        return isDeleted;
    }


    public void setDeleted(boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }


    public String getManufacturer()
    {
        return manufacturer;
    }


    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

}
