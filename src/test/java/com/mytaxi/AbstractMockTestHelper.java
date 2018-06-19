package com.mytaxi;

import java.time.ZonedDateTime;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarLinkDO;
import com.mytaxi.domainvalue.CarType;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;


@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractMockTestHelper
{

    public CarDTO getMockedCarData()
    {
        return CarDTO
            .builder()
            .licensePlate("BDC-SDD-123")
            .seatCount(10L)
            .carType(CarType.SEDAN)
            .manufacturer("Hundai")
            .rating(5)
            .build();
    }


    public CarDO getCarDO()
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
        GeoCoordinate geoCoordinate = new GeoCoordinate(90, 90);

       return  DriverDTO.newBuilder().setUsername("test").setPassword("test").setOnlineStatus(OnlineStatus.ONLINE).setCoordinate(geoCoordinate).createDriverDTO();
    }


   
    public DriverCarLinkDO getDriverCar()
    {
        DriverCarLinkDO driverCar = new DriverCarLinkDO();
        driverCar.setDriverId(1L);
        driverCar.setCarId(1L);
        driverCar.setId(1);
        return driverCar;
    }
}
