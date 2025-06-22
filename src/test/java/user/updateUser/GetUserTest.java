package user.updateUser;

import dto.GetUserResponseDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import services.PetStoreApi;

import static org.hamcrest.Matchers.lessThan;

@SpringBootTest(classes = {PetStoreApi.class})
public class GetUserTest {

    @Autowired
    PetStoreApi api;

    //Проверка полей ответа при получении информации о пользователе по имени пользователя
    @Test
    public void getUser() {
        GetUserResponseDTO getUserResponse = api.getUser("string")
                .extract()
                .body()
                .as(GetUserResponseDTO.class);
        Assertions.assertAll(
                "Проверка ответа обновления пользователя по имени пользователя",
                () -> Assertions.assertEquals(getUserResponse.getUsername(), "string",
                        "Имя искомого пользователя не соответствует ожидаемому"),
                () -> Assertions.assertEquals(getUserResponse.getFirstName(), "string",
                        "Фамилия искомого пользователя не соответствует ожидаемому"),
                () -> Assertions.assertEquals(getUserResponse.getLastName(), "string",
                        "Отчество искомого пользователя не соответствует ожидаемому"),
                () -> Assertions.assertEquals(getUserResponse.getEmail(), "string",
                        "Емайл искомого пользователя не соответствует ожидаемому"),
                () -> Assertions.assertEquals(getUserResponse.getPassword(), "string",
                        "Пароль искомого пользователя не соответствует ожидаемому"),
                () -> Assertions.assertEquals(getUserResponse.getPhone(), "string",
                        "Номер телефона искомого пользователя не соответствует ожидаемому"),
                () -> Assertions.assertEquals(getUserResponse.getUserStatus(), 0,
                        "Статус код искомого пользователя не соответствует ожидаемому")
        );
    }

    //Проверка статус кода, json схемы и времени ответа
    @Test
    public void getUserSchemaSCTime() {
        api.getUser("string")
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(3000L))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/GetUser.json"));
    }

}
