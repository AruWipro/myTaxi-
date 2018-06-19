package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarLinkDO;
import com.mytaxi.domainobject.DriverDO;

public interface DriverCarLnkRepository extends JpaRepository<DriverCarLinkDO, Long>
{

    DriverCarLinkDO findByDriverIdAndCarId(long driverId, long carId);

    @Query("SELECT driver FROM CarDO car, DriverDO driver, DriverCarLinkDO driverCar WHERE "
        + "driverCar.carId = car.carId AND driverCar.driverId = driver.id "
        + "AND (:#{#carData.licensePlate}=car.licensePlate OR :#{#carData.seatCount} = car.seatCount OR :#{#carData.carType} = car.carType " +
        "OR :#{#carData.rating} = car.rating)")
    List<DriverDO> findDriverByCarDetails(@Param("carData") CarDO carData);

}
