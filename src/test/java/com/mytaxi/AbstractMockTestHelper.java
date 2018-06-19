package com.mytaxi;

import java.time.ZonedDateTime;

import org.junit.runner.RunWith;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.CarType;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.dto.DriverData;
import com.mytaxi.entity.*;

public abstract class AbstractMockTestHelper
{

    public CarDTO getMockedCarData()
    {
        return CarDTO.builder()
            .licensePlate("BDC-SDD-123")
            .seatCount(10L)
            .carType(CarType.SEDAN)
            .manufacturer("Hundai")
            .rating(5)
            .build();
    }
    
    
    public CarDO getCar()
    {
        CarDO car = new CarDO();
        car.setCarId(1L);
        car.setSeatCount(2L);
        car.setRating(5);
        car.setDateRegistered(ZonedDateTime.now());
        car.setLicensePlate("ABV101");
        car.setCarType(CarType.MICRO);
        car.setManufacturer("Benz");
        return car;
    }


   


    public DriverDTO getDriver()
    {
        DriverDTO driver = new DriverDTO();
        driver.set("test");
        driver.setPassword("test");
        driver.setOnlineStatus(OnlineStatus.ONLINE);
        GeoCoordinate geoCoordinate = new GeoCoordinate(90, 90);
        driver.setCoordinate(geoCoordinate);
        return driver;
    }


    public DriverData getDriverData()
    {
        GeoCoordinate geoCoordinate = new GeoCoordinate(90, 90);
        return DriverData.newBuilder()
            .setId(1L)
            .setPassword("test")
            .setUsername("test")
            .setCoordinate(geoCoordinate)
            .createDriverDTO();
    }


    public DriverCar getDriverCar()
    {
        DriverCar driverCar = new DriverCar();
        driverCar.setCarId(1L);
        driverCar.setDriverId(1L);
        driverCar.setCarId(1L);
        return driverCar;
    }
}
