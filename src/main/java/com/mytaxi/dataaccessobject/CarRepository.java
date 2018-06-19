package com.mytaxi.dataaccessobject;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytaxi.domainobject.CarDO;

public interface CarRepository extends JpaRepository<CarDO,Long>
{

}
