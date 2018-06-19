package com.mytaxi.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@Table(name = "DRIVER_CAR_LTB")
@NoArgsConstructor
@AllArgsConstructor
public class DriverCarLinkDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, name = "CAR_ID")
    @NonNull
    private Long carId;

    @Column(unique = true, name = "DRIVER_ID")
    @NonNull
    private Long driverId;

}
