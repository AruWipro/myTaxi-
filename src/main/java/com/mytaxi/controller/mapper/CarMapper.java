package com.mytaxi.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;

public class CarMapper
{
    public static CarDTO getCarDTO(CarDO carDO)
    {
        CarDTO.CarDTOBuilder carBuilder = CarDTO.builder().id(carDO.getCarId())
                                                          .carType(carDO.getCarType())
                                                          .seatCount(carDO.getSeatCount())
                                                          .rating(carDO.getRating())
                                                          .licensePlate(carDO.getLicensePlate())
                                                          .manufacturer(carDO.getManufacturer());
           

        return carBuilder.build();
    }

    public static CarDO createCarDO(CarDTO carDTO)
    {
        return new CarDO(carDTO.getLicensePlate(),carDTO.getSeatCount(),carDTO.getCarType(),carDTO.getManufacturer(),carDTO.getRating());
        
    }
    
    public static List<CarDTO> getCarDTOList(List<CarDO> carsDO){
         return carsDO.stream()
                      .map(CarMapper::getCarDTO)
                      .collect(Collectors.toList());
    }

}
