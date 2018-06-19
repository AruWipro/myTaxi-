package com.mytaxi.util;

/**
 * @author Aravind piratla
 * 
 * This Enum returns the Exception messages.The Message can be customized for Internationalization.
 *
 */
public enum ExceptionMessages
{
    CAR_ALREAD_IN_USE("Sorry this car is already seleced by another user. Please select another Car."),
    DRIVER_NOT_ASSOC_CAR("Sorry we cannot deselect the car as the driver is currently not assosciated with this car"),
    CAR_ALREADY_EXISTS("Sorry a car alread exists with the same license plate"),
    CAR_NOT_FOUND("Sorry car with given request attributes not found"),
    DRIVER_NOT_FOUND("Sorry driver with given request attributes not found");
    
    
    private String message;


    private ExceptionMessages(String message)
    {
        this.setMessage(message);
    }


    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }


    public void setMessage(String message)
    {
        this.message = message;
    }

}
