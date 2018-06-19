package com.mytaxi.datatransferobject;

import com.mytaxi.domainvalue.CarType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarSearchDTO
{
    private String licensePlate;

    private Long seatCount;

    private int rating;

    private CarType carType;

    private String manufacturer;


}
