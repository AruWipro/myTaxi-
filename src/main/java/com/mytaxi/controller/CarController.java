package com.mytaxi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;


@RestController
@RequestMapping("v1/cars")
public class CarController
{
    
    @Autowired
    private CarService carServiceImpl;


    /**
     * Gets the car.
     *
     * @param carId the car id
     * @return the CarDTO
     * @throws EntityNotFoundException the entity not found exception
     */
    @GetMapping("/{carId}")
    public CarDTO getCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        return carServiceImpl.find(carId);
    }


    /**
     * Gets the all cars.
     *
     * @return the all cars
     */
    @GetMapping("/")
    public List<CarDTO> getAllCars()
    {
        return CarMapper.getCarDTOList(carServiceImpl.getAllCars());
    }


    /**
     * Creates the car.
     *
     * @param carDTO the car DTO
     * @return the carDTO
     * @throws ConstraintsViolationException the constraints violation exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO carDO = CarMapper.createCarDO(carDTO);
        return CarMapper.getCarDTO(carServiceImpl.create(carDO));
    }


    /**
     * Delete car.
     *
     * @param carId the car id
     * @throws EntityNotFoundException the entity not found exception
     */
    @DeleteMapping("/{carId}")
    public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        carServiceImpl.delete(carId);
    }

}
