package com.mytaxi.service.car;

import java.util.List;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.service.CrudService;

public interface CarService extends CrudService<CarDTO,CarDO>
{
    
    List<CarDTO> getAllCars();

}
