package api.rest;

import apirest.CarController;
import dto.CarDto;
import enums.Fuel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTestRest extends CarController {

  @Test
  public void addNewCarPositiveTest(){
    int i = new Random().nextInt(1000)+1000;
    CarDto car = CarDto.builder()
        .serialNumber("777-"+i)
        .manufacture("Opel")
        .model("Astra")
        .year("2003")
        .fuel(Fuel.PETROL.getFuel())
        .seats(4)
        .carClass("A")
        .pricePerDay(100.23)
        .city("Rehovot")
        .build();
    Assert.assertEquals(addNewCar(car).getStatusCode(), 200);
  }

}
