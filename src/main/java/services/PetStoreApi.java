package services;

import dto.UpdateUserDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;

import static io.restassured.RestAssured.given;

public class PetStoreApi {

    private RequestSpecification spec;
    private String path;
    private String param = "/{username}";

    public PetStoreApi(@Value("${base.url}") String baseUrl,
                       @Value("${path.user}") String path) {
        spec = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON);
        this.path = path;
    }

    //формируем запрос для обновления юзера по его имени
    public ValidatableResponse updateUser(String userName, UpdateUserDTO userDTO) {
        return given(spec)
                .basePath(path + param)
                .pathParam("username", userName)
                .body(userDTO)
                .log().all()
                .when()
                .put()
                .then()
                .log().all();
    }

    public ValidatableResponse getUser(String userName) {
        return given(spec)
                .basePath(path + param)
                .pathParam("username", userName)
                .log().all()
                .when()
                .get()
                .then()
                .log().all();
    }

}
