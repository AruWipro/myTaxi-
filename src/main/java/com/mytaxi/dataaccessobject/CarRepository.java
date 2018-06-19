package com.mytaxi.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.mytaxi.domainobject.CarDO;

public interface CarRepository extends CrudRepository<CarDO,Long>
{

}
