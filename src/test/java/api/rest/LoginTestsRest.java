package api.rest;

import apirest.AuthenticationController;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginTestsRest extends AuthenticationController {

  RegistrationBodyDto user;

  @BeforeClass
  public void registrationUser(){
    int i = new Random().nextInt(1000);
    user = RegistrationBodyDto.builder()
        .username("tester"+i+"@gmail.com")
        .password("Qwerty123!")
        .firstName("Tester")
        .lastName("Testorovich")
        .build();
    System.out.println(registrationLogin(user, REGISTRATION_URL).getStatusCode());
  }

  @Test
  public void loginPositiveTest(){
    Assert.assertEquals(registrationLogin(user, LOGIN_URL).getStatusCode(), 200);
  }



}
