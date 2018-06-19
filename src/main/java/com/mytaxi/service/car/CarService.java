package com.mytaxi.service.car;

import java.util.List;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.CrudService;

public interface CarService extends CrudService<CarDTO>
{
    
    List<CarDTO> getAllCars();
    
    void updateCar(CarDTO carDTO,long carId) throws EntityNotFoundException;

}
