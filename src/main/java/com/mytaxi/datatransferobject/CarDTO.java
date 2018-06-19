package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domainvalue.CarType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "Licence Plate is mandatory")
    private String licensePlate;

    @NotNull(message = "Seat Count is mandatory")
    private Long seatCount;

    private Integer rating;

    private CarType carType;

    private String manufacturer;

 
 
}
