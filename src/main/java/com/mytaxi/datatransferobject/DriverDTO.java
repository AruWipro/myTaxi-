package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO
{
    @JsonIgnore
    private Long id;
   
    @NotNull(message = "Username can not be null!")
    private String username;

    @NotNull(message = "Password can not be null!")
    private String password;
    
    private GeoCoordinate coordinate;

    private CarDTO carDTO;

    private OnlineStatus onlineStatus;
    
    @JsonIgnore
    private boolean isDriverDeleted;

    public DriverDTO()
    {}

    public void setCarDTO(CarDTO carDTO)
    {
        this.carDTO = carDTO;
    }
    public CarDTO getCarDTO()
    {
        return carDTO;
    }
    
    @JsonIgnore
    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public OnlineStatus getOnlineStatus()
    {
        return onlineStatus;
    }
    
    public void setOnlineStatus(OnlineStatus onlineStatus)
    {
        this.onlineStatus = onlineStatus;
    }


    public static DriverDTOBuilder newBuilder()
    {
        return new DriverDTOBuilder();
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public GeoCoordinate getCoordinate()
    {
        return coordinate;
    }

    public void setCoordinate(GeoCoordinate coordinate)
    {
        this.coordinate = coordinate;
    }
    
    private DriverDTO(Long id, String username, String password, GeoCoordinate coordinate, OnlineStatus status,boolean isDriverDeleted)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.coordinate = coordinate;
        this.onlineStatus = status;
        this.isDriverDeleted=isDriverDeleted;
    }
    @JsonIgnore
    public boolean isDriverDeleted()
    {
        return isDriverDeleted;
    }

    public void setDriverDeleted(boolean isDriverDeleted)
    {
        this.isDriverDeleted = isDriverDeleted;
    }

    public static class DriverDTOBuilder
    {
        private Long id;
        private String username;
        private String password;
        private GeoCoordinate coordinate;
        private OnlineStatus status;
        private boolean isDriverDeleted;
        


        public DriverDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public DriverDTOBuilder setUsername(String username)
        {
            this.username = username;
            return this;
        }


        public DriverDTOBuilder setPassword(String password)
        {
            this.password = password;
            return this;
        }


        public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate)
        {
            this.coordinate = coordinate;
            return this;
        }


        public DriverDTOBuilder setOnlineStatus(OnlineStatus status)
        {
            this.status = status;
            return this;
        }
        
        public DriverDTOBuilder setisDriverDeleted(boolean isDriverDeleted) {
            this.isDriverDeleted=isDriverDeleted;
            return this;
        }


        public DriverDTO createDriverDTO()
        {
            return new DriverDTO(id, username, password, coordinate, status,isDriverDeleted);
        }

    }

}
