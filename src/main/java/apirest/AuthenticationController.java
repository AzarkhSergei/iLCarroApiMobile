package apirest;

import dto.RegistrationBodyDto;
import interfaces.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationController implements BaseApi {

  public Response registrationLogin(RegistrationBodyDto user, String url){
    return given()
        .body(user)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL+url)
        .thenReturn();
  }

  public Response registrationLoginHTTP(RegistrationBodyDto user, String url){
    return given()
        .body(user)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL_HTTP+url)
        .thenReturn();
  }


}
