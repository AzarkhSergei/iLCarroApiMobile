package api.okhttp;

import dto.CarDto;
import dto.RegistrationBodyDto;
import dto.TokenDto;
import enums.Fuel;
import interfaces.BaseApi;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class AddNewCarTestsOkHttp implements BaseApi {

  TokenDto tokenDto = new TokenDto();

  @BeforeClass
  public void login(){
    RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
        .username("tester19871987@gmail.com")
        .password("Qwerty123!")
        .build();
    RequestBody requestBody = RequestBody.create(GSON.toJson(registrationBodyDto), JSON);

    Request request = new Request.Builder()
        .url(BASE_URL+LOGIN_URL)
        .post(requestBody)
        .build();

    Response response;
    try {
      response = OK_HTTP_CLIENT.newCall(request).execute();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      tokenDto = GSON.fromJson(response.body().string(), TokenDto.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Token: "+tokenDto.getAccessToken());
  }

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

    RequestBody requestBody = RequestBody.create(GSON.toJson(car), JSON);
    Request request = new Request.Builder()
        .url(BASE_URL+ADD_NEW_CAR_URL)
        .addHeader("Authorization", tokenDto.getAccessToken())
        .post(requestBody)
        .build();

    Response response;
    String responseJson;

    try {
      response = OK_HTTP_CLIENT.newCall(request).execute();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Assert.assertTrue(response.isSuccessful());
  }

}
