package user.updateUser;

import dto.UpdateUserDTO;
import dto.UpdateUserResponseDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import services.PetStoreApi;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.lessThan;

@SpringBootTest(classes = {PetStoreApi.class})
public class UpdateUserTest {

    @Autowired
    PetStoreApi api;

    //Проверяю обновление пользователя по имени пользователя, поля ответа
    @ParameterizedTest
    @MethodSource("dataProvider")
    public void updateUser(UpdateUserDTO userDTO) {
        UpdateUserResponseDTO updatedUserResponse = api.updateUser("string", userDTO)
                .extract()
                .body()
                .as(UpdateUserResponseDTO.class);
        Assertions.assertAll(
                "Проверка ответа обновления пользователя по имени пользователя",
                () -> Assertions.assertEquals(updatedUserResponse.getCode(), 200L,
                        "Код ответа не соответствует ожидаемому"),
                () -> Assertions.assertEquals(updatedUserResponse.getType(), "unknown",
                        "Тип ответа не соответствует ожидаемому"),
                () -> Assertions.assertEquals(updatedUserResponse.getMessage(), "0",
                        "Сообщение ответа не соответствует ожидаемому")
        );
    }

    //Проверка статус кода, json схемы и времени ответа
    @ParameterizedTest
    @MethodSource("dataProvider")
    public void updateUserSchemaSCTime(UpdateUserDTO userDTO) {
        api.updateUser("hr", userDTO)
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(5000L))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/UpdateUser.json"));
    }

    //Проверка обновления юзера по имени несуществующего юзера
    //Ожидаю получить 404
    @Test
    public void updateUserNotFound() {
        UpdateUserDTO user = UpdateUserDTO.builder()
                .userName("CocaCola")
                .email("@coca.com")
                .phone("333-165")
                .build();
        api.updateUser("654укп754цу54кпк564еп", user)
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .time(lessThan(1000L));
    }

    private static Stream<Arguments> dataProvider() {

        UpdateUserDTO userDTO = UpdateUserDTO.builder()
                .userName("Bella1")
                .firstName("Harrison")
                .lastName("First")
                .email("@mail.com")
                .phone("123-234")
                .password("1q2w")
                .build();

        UpdateUserDTO userDTOnoName = UpdateUserDTO.builder()
                .firstName("Harrison1")
                .lastName("First")
                .email("@mail.com")
                .phone("123-234")
                .password("1q2w")
                .build();

        UpdateUserDTO userDTOnoMail = UpdateUserDTO.builder()
                .userName("Bella2")
                .firstName("Harrison")
                .lastName("First")
                .phone("123-234")
                .password("1q2w")
                .build();

        return Stream.of(
                Arguments.of(userDTO),
                Arguments.of(userDTOnoName),
                Arguments.of(userDTOnoMail)
        );
    }
}
