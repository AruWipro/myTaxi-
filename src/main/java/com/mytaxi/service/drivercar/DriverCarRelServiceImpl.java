package com.mytaxi.service.drivercar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytaxi.dataaccessobject.DriverCarLnkRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarLinkDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.EntityNotFoundException;

@Service
public class DriverCarRelServiceImpl implements DriverCarRelService
{

    @Autowired
    private DriverCarLnkRepository driverCarLnkRepo;


    @Override
    public void save(DriverCarLinkDO driverCarLinkDO)
    {
        driverCarLnkRepo.save(driverCarLinkDO);
    }


    @Override
    public void delete(DriverCarLinkDO driverCarLinkDO)
    {
        driverCarLnkRepo.delete(driverCarLinkDO);
    }


    @Override
    public DriverCarLinkDO getDriverCarEntity(long driverId, long carId) throws EntityNotFoundException
    {
        return driverCarLnkRepo.findByDriverIdAndCarId(driverId,carId);
    }


    @Override
    public List<DriverDO> getDriverByCarDetails(CarDO carDO) throws EntityNotFoundException
    {
        return driverCarLnkRepo.findDriverByCarDetails(carDO);
    }

}
