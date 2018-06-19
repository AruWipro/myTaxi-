package com.mytaxi.service.drivercar;

import java.util.List;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarLinkDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.EntityNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Interface DriverCarRelService.
 */
public interface DriverCarRelService
{
    
    /**
     * This interface is used to save  the Driver-Car Association Enitity.
     *
     * @param driverCarLinkDO the driver car link DO
     * @return DriverCarLinkDO
     */
    void save(DriverCarLinkDO driverCarLinkDO);


    /**
     * Deletes the Driver Car Entity.
     *
     * @param driverCarLinkDO the driver car link DO
     */
    void delete(DriverCarLinkDO driverCarLinkDO);
    
    /**
     * Returns the driver car entity if that combination exists.
     *
     * @param driverId the driver id
     * @param carId the car id
     * @return tDriverCarLinkDO
     * @throws EntityNotFoundException the entity not found exception
     */
    DriverCarLinkDO getDriverCarEntity(long driverId,long carId) throws EntityNotFoundException;


    /**
     * Get the drivers by car details.
     *
     * @param carDO the car DO
     * @return List<DriverDO>
     * @throws EntityNotFoundException 
     */
    List<DriverDO> getDriverByCarDetails(CarDO carDO) throws EntityNotFoundException;

}
