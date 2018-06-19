package com.mytaxi.service.driver;

import java.util.List;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.DriverOfflineException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.CrudService;

/**
 * The Interface DriverService.
 */
public interface DriverService extends CrudService<DriverDTO,DriverDO>
{
    
    /**
     * Update location.
     *
     * @param id the id
     * @param longitude the longitude
     * @param latitude the latitude
     * @throws EntityNotFoundException the entity not found exception
     */
    void updateLocation(long id, double longitude, double latitude) throws EntityNotFoundException;

    /**
     * Find Driver by Stauts.
     *
     * @param onlineStatus the online status
     * @return the list
     */
    List<DriverDTO> find(OnlineStatus onlineStatus);
    
    /**
     * Select a car by Driver.
     *
     * @param driverId the driver id
     * @param carId the car id
     * @return the DriverDTO
     * @throws CarAlreadyInUseException if the car is already in use/Driver already assoicated with driver
     * @throws EntityNotFoundException if car/driver not found
     * @throws DriverOfflineException if the driver is offline 
     */
    DriverDTO selectCar(long driverId,long carId) throws CarAlreadyInUseException, EntityNotFoundException, DriverOfflineException;

    /**
     * Update the online status of a Driver.
     *
     * @param driverId the driver id
     * @param status the status
     * @throws EntityNotFoundException if driver is not Found
     */
    void updateOnlineStatus(long driverId, OnlineStatus status)throws EntityNotFoundException;
    
    /**
     * Deselect car.
     *
     * @param driverId the driver id
     * @param carId the car id
     * @throws EntityNotFoundException the entity not found exception
     * @throws DriverOfflineException the driver offline exception
     */
    void deselectCar(long driverId,long carId) throws EntityNotFoundException, DriverOfflineException;
    
    /**
     * Find driver by car params.
     *
     * @param carDTO the car DTO
     * @return the list
     * @throws EntityNotFoundException the entity not found exception
     */
    List<DriverDTO> findDriverByCarParams(CarDTO carDTO) throws EntityNotFoundException;


}
