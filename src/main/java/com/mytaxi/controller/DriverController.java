package com.mytaxi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverOfflineException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DriverService;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    /** The driver service. */
    private final DriverService driverService;


    /**
     * Instantiates a new driver controller.
     *
     * @param driverService the driver service
     */
    @Autowired
    public DriverController(final DriverService driverService)
    {
        this.driverService = driverService;
    }


    /**
     * Gets the driver.
     *
     * @param driverId the driver id
     * @return the driver
     * @throws EntityNotFoundException the entity not found exception
     */
    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        return driverService.find(driverId);
    }


    /**
     * Select car.
     *
     * @param driverId the driver id
     * @param carId the car id
     * @throws CarAlreadyInUseException the car already in use exception
     * @throws EntityNotFoundException the entity not found exception
     * @throws DriverOfflineException the driver offline exception
     */
    @PostMapping("/selectCar")
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO selectCar(@RequestParam long driverId, @RequestParam long carId) throws CarAlreadyInUseException, EntityNotFoundException, DriverOfflineException
    {
        return driverService.selectCar(driverId, carId);

    }


    /**
     * Deselect car.
     *
     * @param driverId the driver id
     * @param carId the car id
     * @throws EntityNotFoundException the entity not found exception
     * @throws DriverOfflineException the driver offline exception
     */
    @PostMapping("/deselectCar")
    @ResponseStatus(HttpStatus.CREATED)
    public void deselectCar(@RequestParam long driverId, @RequestParam long carId) throws EntityNotFoundException, DriverOfflineException
    {
        driverService.deselectCar(driverId, carId);

    }


    /**
     * Creates the driver.
     *
     * @param driverDTO the driver DTO
     * @return the driver DTO
     * @throws ConstraintsViolationException the constraints violation exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        return driverService.create(driverDTO);
    }


    /**
     * Delete driver.
     *
     * @param driverId the driver id
     * @throws EntityNotFoundException the entity not found exception
     */
    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    /**
     * Update location.
     *
     * @param driverId the driver id
     * @param longitude the longitude
     * @param latitude the latitude
     * @throws ConstraintsViolationException the constraints violation exception
     * @throws EntityNotFoundException the entity not found exception
     */
    @PutMapping("/{driverId}")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    /**
     * Find drivers.
     *
     * @param onlineStatus the online status
     * @return the list
     * @throws ConstraintsViolationException the constraints violation exception
     * @throws EntityNotFoundException the entity not found exception
     */
    @GetMapping
    public List<DriverDTO> findDriversByStatus(@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return driverService.findByStatus(onlineStatus);
    }


    /**
     *
     * Update driver online status.
     * @param driverId the driver id
     * @param status the status
     * @throws EntityNotFoundException the entity not found exception
     */
    @PutMapping("/status/{driverId}")
    public void updateDriverOnlineStatus(@Valid @PathVariable long driverId, @RequestParam OnlineStatus status) throws EntityNotFoundException
    {
        driverService.updateOnlineStatus(driverId, status); 

    }


    /**
     * Find drivers by car attributes.
     * 
     *
     * @param serachDTO the CarSearchDTO
     * @return List<DriverDTO>
     * @throws EntityNotFoundException 
     */
    @GetMapping("/search")
    public List<DriverDTO> searchDriversByCarAttributes(
        @RequestParam(required = false) String licensePlate, @RequestParam(required = false) Long seatCount, @RequestParam(required = false) Integer rating)
        throws EntityNotFoundException
    {
        CarDTO carDTO = CarDTO.builder().licensePlate(licensePlate).seatCount(seatCount).rating(rating).build();
        return driverService.findDriverByCarParams(carDTO);

    }

}
