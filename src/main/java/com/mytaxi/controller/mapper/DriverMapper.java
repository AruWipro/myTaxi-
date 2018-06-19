package com.mytaxi.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;

public class DriverMapper
{
    public static DriverDO makeDriverDO(DriverDTO driverDTO)
    {
        return new DriverDO(driverDTO.getUsername(), driverDTO.getPassword(), driverDTO.getOnlineStatus());
    }


    public static DriverDTO makeDriverDTO(DriverDO driverDO)
    {
        DriverDTO.DriverDTOBuilder driverDTOBuilder =
            DriverDTO
                .newBuilder()
                .setId(driverDO.getId())
                .setPassword(driverDO.getPassword())
                .setUsername(driverDO.getUsername())
                .setOnlineStatus(driverDO.getOnlineStatus());

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null && coordinate.getPoint() != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers)
    {
        return drivers
            .parallelStream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }


    public static DriverDTO constructDriverWithCar(DriverDO driverDO, CarDTO carDTO)
    {
        DriverDTO driverData = makeDriverDTO(driverDO);
        driverData.setCarDTO(carDTO);
        return driverData;
    }
}
