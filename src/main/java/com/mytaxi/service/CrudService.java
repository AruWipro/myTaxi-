package com.mytaxi.service;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CrudService<DTO,DO>
{

    DTO find(Long id) throws EntityNotFoundException;

    DTO create(DO domainObject) throws ConstraintsViolationException;

    void delete(Long id) throws EntityNotFoundException;

    
}
