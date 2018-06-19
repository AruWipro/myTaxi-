package com.mytaxi.service;

import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CrudService<DTO>
{

    DTO find(Long id) throws EntityNotFoundException;

    DTO create(DTO domainTransferObject) throws ConstraintsViolationException;

    void delete(Long id) throws EntityNotFoundException;

    
}
