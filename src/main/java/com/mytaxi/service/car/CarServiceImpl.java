package com.mytaxi.service.car;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.util.ExceptionMessages;

@Service
public class CarServiceImpl implements CarService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;


    @Override
    public CarDTO find(Long carId) throws EntityNotFoundException
    {
        CarDO carEntity = getCarEntityById(carId);

        return CarMapper.getCarDTO(carEntity);
    }


    @Override
    public List<CarDTO> getAllCars()
    {

        Iterable<CarDO> iterables = this.carRepository.findAll();
        List<CarDO> listOfCars = new ArrayList<>();
        iterables.forEach(listOfCars::add);
        return CarMapper.getCarDTOList(listOfCars);
    }


    @Override
    public CarDTO create(CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO carDO;
        try
        {
            carDO = carRepository.save(CarMapper.createCarDO(carDTO));
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(ExceptionMessages.CAR_ALREADY_EXISTS.getMessage());
        }
        return CarMapper.getCarDTO(carDO);
    }


    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        //Getting the Car
        CarDO car = getCarEntityById(carId);
        //Setting Status to Deleted, if it exists
        car.setDeleted(true);
        //Persisting the Entity
        carRepository.save(car);
    }


    private CarDO getCarEntityById(Long carId) throws EntityNotFoundException
    {
        return this.carRepository
            .findById(carId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find a Car with id: " + carId));
    }


    @Override
    @Transactional
    public void updateCar(CarDTO carDTO,long carId) throws EntityNotFoundException
    {
        CarDO carDO = getCarEntityById(carId);
        carDO.setCarType(carDTO.getCarType());
        carDO.setLicensePlate(carDTO.getLicensePlate());
        carDO.setManufacturer(carDTO.getManufacturer());
        carDO.setRating(carDTO.getRating());
        carDO.setSeatCount(carDTO.getSeatCount());
        carDO.setDeleted(carDTO.isDeleted());

        carRepository.save(carDO);
    }

}
