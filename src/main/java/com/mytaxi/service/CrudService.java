package com.mytaxi.service;

import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Interface CrudService- Generic CRUD Implementation.
 *
 * @param <DTO> the generic type
 */
public interface CrudService<DTO>
{

    /**
     * Retrives a DTO based on id
     *
     * @param id the id
     * @return the dto
     * @throws EntityNotFoundException the entity not found exception
     */
    DTO find(Long id) throws EntityNotFoundException;

    /**
     * Creates the DTO.
     *
     * @param domainTransferObject the domain transfer object
     * @return the dto
     * @throws ConstraintsViolationException the constraints violation exception
     */
    DTO create(DTO domainTransferObject) throws ConstraintsViolationException;

    /**
     * Delete the DTO based on id.
     *
     * @param id the id
     * @throws EntityNotFoundException the entity not found exception
     */
    void delete(Long id) throws EntityNotFoundException;

    
}
