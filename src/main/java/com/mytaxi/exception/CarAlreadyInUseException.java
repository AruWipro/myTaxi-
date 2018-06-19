package com.mytaxi.exception;

/**
 * @author Aravind Piratla
 *
 */
public class CarAlreadyInUseException extends Exception
{
    private static final long serialVersionUID = 1L;


    public CarAlreadyInUseException(final String message)
    {
        super(message);
    }
}
