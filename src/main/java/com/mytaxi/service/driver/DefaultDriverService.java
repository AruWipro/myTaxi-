package com.mytaxi.service.driver;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverCarLinkDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverOfflineException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.drivercar.DriverCarRelService;
import com.mytaxi.util.ExceptionMessages;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private DriverCarRelService driverCarRelService;


    public DefaultDriverService(final DriverRepository driverRepository)
    {
        this.driverRepository = driverRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDTO find(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverEntity= findDriverChecked(driverId);
        return DriverMapper.makeDriverDTO(driverEntity);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    @Transactional
    public DriverDTO create(DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(DriverMapper.makeDriverDO(driverDTO));
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return DriverMapper.makeDriverDTO(driver);
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setIsDriverDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDTO> findByStatus(OnlineStatus onlineStatus) throws EntityNotFoundException
    {
         List<DriverDO> driversList=driverRepository.findByOnlineStatusAndDeleted(onlineStatus, false);
         return DriverMapper.makeDriverDTOList(driversList);
    }


    /* (non-Javadoc)
     * @see com.mytaxi.service.driver.DriverService#selectCar(long, long)
     */
    @Override
    public DriverDTO selectCar(long driverId, long carId) throws CarAlreadyInUseException, EntityNotFoundException, DriverOfflineException
    {
        //Check if CAR and Driver Entities Exist for the given driver and car IDs
        DriverDO driverEntity = findDriverChecked(driverId);

        CarDTO carDTO = carService.find(carId);

        try
        {   //Driver should not be in deleted state and should be online and Car Should not be in deleted State
            if (!driverEntity.getIsDriverDeleted() && OnlineStatus.ONLINE.equals(driverEntity.getOnlineStatus()) && !carDTO.isDeleted() )
                linkDriverToCar(driverId, carId);
            else if(OnlineStatus.OFFLINE.equals(driverEntity.getOnlineStatus()))
                throw new DriverOfflineException("The Driver " + driverEntity.getUsername() + " is currently Offline. Please try after sometime ");
            else
                throw new CarAlreadyInUseException(ExceptionMessages.CAR_NOT_AVAILABLE.getMessage());
        }
        catch (DataIntegrityViolationException dataIntegrityException)
        {
            throw new CarAlreadyInUseException(ExceptionMessages.CAR_ALREAD_IN_USE.getMessage());
        }

        return DriverMapper.constructDriverWithCar(driverEntity, carDTO);

    }


    @Override
    public void deselectCar(long driverId, long carId) throws DriverOfflineException, EntityNotFoundException
    {

        DriverDO driverEntity = findDriverChecked(driverId);

        carService.find(carId);

        try
        {

            //Assuming driver can De-link a car only if he is in Online
            if (OnlineStatus.ONLINE.equals(driverEntity.getOnlineStatus()))
                delinkDriverCar(driverId, carId);

            else
                throw new DriverOfflineException("The Driver " + driverEntity.getUsername() + " is currently Offline. Please try after sometime ");
        }
        catch (EntityNotFoundException ex)
        {
            throw new EntityNotFoundException(ExceptionMessages.DRIVER_NOT_ASSOC_CAR.getMessage());
        }
        catch (InvalidDataAccessApiUsageException exception)
        {
            throw new EntityNotFoundException(ExceptionMessages.DRIVER_NOT_ASSOC_CAR.getMessage());
        }
    }


    private void delinkDriverCar(long driverId, long carId) throws EntityNotFoundException
    {
        //Check if an Entity of that Combination Exists and delete that Entity
        DriverCarLinkDO driverCarEntity = driverCarRelService.getDriverCarEntity(driverId, carId);
        driverCarRelService.delete(driverCarEntity);

    }


    @Override
    @Transactional
    public void updateOnlineStatus(long driverId, OnlineStatus status) throws EntityNotFoundException
    {
        DriverDO driverEntity = findDriverChecked(driverId);
        driverEntity.setOnlineStatus(status);
        driverRepository.save(driverEntity);
    }

    @Transactional
    private void linkDriverToCar(long driverId, long carId)
    {
        DriverCarLinkDO linkDO = new DriverCarLinkDO();
        linkDO.setCarId(carId);
        linkDO.setDriverId(driverId);
        driverCarRelService.save(linkDO);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository
            .findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find Driver with id: " + driverId));
    }


    /* (non-Javadoc)
     * @see com.mytaxi.service.driver.DriverService#findDriverByCarParams(com.mytaxi.datatransferobject.CarDTO)
     */
    @Override
    public List<DriverDTO> findDriverByCarParams(CarDTO carDTO) throws EntityNotFoundException
    {
        List<DriverDO> driverEntities;
        try
        {
            driverEntities = driverCarRelService.getDriverByCarDetails(CarMapper.createCarDO(carDTO));

        }
        catch (EntityNotFoundException ex)
        {
            throw new EntityNotFoundException(ExceptionMessages.DRIVER_NOT_FOUND.getMessage());
        }
        return DriverMapper.makeDriverDTOList(driverEntities);

    }
}
