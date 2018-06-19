package com.mytaxi.domainvalue;

import java.util.Arrays;

/**
 * The Enum CarType.
 */
public enum CarType
{

    SEDAN("SED"),

    SUV("SUV"),

    MINI("M"),

    MICRO("MC");

    /** The car code. */
    private String carCode;


    /**
     * Gets the car code.
     *
     * @return the car code
     */
    public String getCarCode()
    {
        return carCode;
    }


    /**
     * Sets the car code.
     *
     * @param carCode the new car code
     */
    public void setCarCode(String carCode)
    {
        this.carCode = carCode;
    }


    /**
     * Instantiates a new car type.
     *
     * @param carCode the car code
     */
    CarType(String carCode)
    {
        this.carCode = carCode;
    }


    /**
     * Parse text and return equivalent enumeration.
     * 
     * @param code
     * @return
     */
    public static CarType parseText(String carCode)
    {
        return Arrays.stream(values()).filter(e -> e.getCarCode().equals(carCode)).findAny().orElseThrow(() -> new RuntimeException("Unsupported carCode: " + carCode));

    }

}
