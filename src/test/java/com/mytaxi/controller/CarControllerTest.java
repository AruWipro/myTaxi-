package com.mytaxi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.AbstractMockTestHelper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.service.car.CarService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CarController.class, secure = false)
public class CarControllerTest extends AbstractMockTestHelper
{

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    @InjectMocks
    private CarController carController;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(CarController.class);
    }


    @Before
    public void init()
    {
        mvc = MockMvcBuilders.standaloneSetup(carController).dispatchOptions(true).build();
    }


    @Test
    public void testGetCar() throws Exception
    {
        CarDTO carDTO = getMockedCarData();
        doReturn(carDTO).when(carService).find(any(Long.class));
        carController.getCar(1L);
        MvcResult result =
            mvc.perform(get("/v1/cars/{carId}", 1))
               .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("BDC-SDD-123"));
    }


    @Test
    public void getAllCars() throws Exception
    {
        List<CarDTO> cars = Collections.singletonList(getMockedCarData());
        doReturn(cars).when(carService).getAllCars();
        carController.getAllCars();
        MvcResult result =
            mvc
                .perform(get("/v1/cars/"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("Hundai"));
    }


    @Test
    public void createCar() throws Exception
    {
        CarDTO carDTO = getMockedCarData();
        String jsonInString = mapper.writeValueAsString(carDTO);
        doReturn(carDTO).when(carService).create(any(CarDTO.class));
        carController.createCar(carDTO);
        MvcResult result =
            mvc
                .perform(
                    post("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(jsonInString))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("11.0"));
    }


    @Test
    public void updateCar() throws Exception
    {
        CarData carData = getMockedCarData();
        String jsonInString = mapper.writeValueAsString(carData);
        doNothing().when(carFacade).update(any(CarData.class));
        carController.updateCar(carData);
        MvcResult result =
            mvc
                .perform(
                    put("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(jsonInString))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


    @Test
    public void deleteCar() throws Exception
    {
        doNothing().when(carFacade).delete(any(Long.class));
        carController.deleteCar(1L);
        MvcResult result =
            mvc
                .perform(delete("/v1/cars/{carId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

}
