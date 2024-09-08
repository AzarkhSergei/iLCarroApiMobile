package apirest;

import dto.CarDto;
import dto.RegistrationBodyDto;
import dto.TokenDto;
import interfaces.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class CarController implements BaseApi {

  TokenDto token;

  @BeforeSuite
  public void login(){
    RegistrationBodyDto user = RegistrationBodyDto.builder()
        .username("tester314@gmail.com")
        .password("Qwerty123!")
        .build();
    token = given()
        .body(user)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL+LOGIN_URL)
        .as(TokenDto.class);
  }

  public Response addNewCar(CarDto car) {
    return given()
        .body(car)
        .contentType(ContentType.JSON)
        .header("Authorization", token.getAccessToken())
        .when()
        .post(BASE_URL+ADD_NEW_CAR_URL)
        .thenReturn();
  }


}
