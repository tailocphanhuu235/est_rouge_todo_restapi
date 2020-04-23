package est.rouge.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import est.rouge.dto.TodoRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class TodoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * API: POST: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * { "work_name": null, "startDate": "2020/06/22", "endDate": "2020/08/30",
     * "status": 1 } </br>
     * EXPECTED:</br>
     * { "errors": [ { "code": "ERROR_CODE_003", "message": "must not be null",
     * "item": "workName" } ] }
     */
    @Test
    public void registerTodo_whenWorkNameIsNull_thenStatus400() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setWorkName(null);
        todoRequest.setStartDate("2020/06/22");
        todoRequest.setEndDate("2020/08/30");
        todoRequest.setStatus(1);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(todoRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/est/rouge/todos", entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"errors\":[{\"code\":\"ERROR_CODE_003\",\"message\":\"must not be null\",\"item\":\"workName\"}]}";

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: POST: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * { "work_name": "",
     * "startDate": "2020/06/22", "endDate": "2020/08/30", "status": 1 } </br>
     * EXPECTED:</br>
     * { "errors": [ { "code": "ERROR_CODE_002", "message": "size must be between 1
     * and 50", "item": "workName" } ] }
     */
    @Test
    public void registerTodo_whenWorkNameHaveSizeEquals0_thenStatus400() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setWorkName("");
        todoRequest.setStartDate("2020/06/22");
        todoRequest.setEndDate("2020/08/30");
        todoRequest.setStatus(1);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(todoRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/est/rouge/todos", entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"errors\":[{\"code\":\"ERROR_CODE_002\",\"message\":\"size must be between 1 and 50\",\"item\":\"workName\"}]}";

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: POST: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * { "work_name": "todo test111111111111111111111111111111111111111111",
     * "startDate": "2020/06/22", "endDate": "2020/08/30", "status": 1 } </br>
     * EXPECTED:</br>
     * { "errors": [ { "code": "ERROR_CODE_002", "message": "size must be between 1
     * and 50", "item": "workName" } ] }
     */
    @Test
    public void registerTodo_whenWorkNameHaveSizeThan50_thenStatus400() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setWorkName("todo test111111111111111111111111111111111111111111"); // Length = 51
        todoRequest.setStartDate("2020/06/22");
        todoRequest.setEndDate("2020/08/30");
        todoRequest.setStatus(1);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(todoRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/est/rouge/todos", entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"errors\":[{\"code\":\"ERROR_CODE_002\",\"message\":\"size must be between 1 and 50\",\"item\":\"workName\"}]}";

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: POST: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * { "work_name": "Study CI/CD", "startDate": null, "endDate": "2020/08/30",
     * "status": 1 } </br>
     * EXPECTED:</br>
     * { "errors": [ { "code": "ERROR_CODE_003", "message": "must not be null",
     * "item": "startDate" } ] }
     */
    @Test
    public void registerTodo_whenStartDateIsNull_thenStatus400() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setWorkName("Study CI/CD");
        todoRequest.setStartDate(null);
        todoRequest.setEndDate("2020/08/30");
        todoRequest.setStatus(1);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(todoRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/est/rouge/todos", entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"errors\":[{\"code\":\"ERROR_CODE_003\",\"message\":\"must not be null\",\"item\":\"startDate\"}]}";

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: POST: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * { "work_name": "Study CI/CD", "startDate": "123", "endDate": "2020/08/30",
     * "status": 1 } </br>
     * EXPECTED:</br>
     * { "errors": [ { "code": "ERROR_CODE_004", "message": "date is invalid, must be format yyyy/MM/dd",
     * "item": "startDate" } ] }
     */
    @Test
    public void registerTodo_whenStartDateIsInvalidDateTime_thenStatus400() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setWorkName("Study CI/CD");
        todoRequest.setStartDate("123");
        todoRequest.setEndDate("2020/08/30");
        todoRequest.setStatus(1);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(todoRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/est/rouge/todos", entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"errors\":[{\"code\":\"ERROR_CODE_004\",\"message\":\"date is invalid, must be format yyyy/MM/dd\",\"item\":\"startDate\"}]}";

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: POST: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * { "work_name": "Study CI/CD", "startDate": "2020/06/22", "endDate": null,
     * "status": 1 } </br>
     * EXPECTED:</br>
     * { "errors": [ { "code": "ERROR_CODE_003", "message": "must not be null",
     * "item": "endDate" } ] }
     */
    @Test
    public void registerTodo_whenEndDateIsNull_thenStatus400() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setWorkName("Study CI/CD");
        todoRequest.setStartDate("2020/06/22");
        todoRequest.setEndDate(null);
        todoRequest.setStatus(1);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(todoRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/est/rouge/todos", entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"errors\":[{\"code\":\"ERROR_CODE_003\",\"message\":\"must not be null\",\"item\":\"endDate\"}]}";

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: POST: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * { "work_name": "Study CI/CD", "startDate": "2020/06/22", "endDate": "123",
     * "status": 1 } </br>
     * EXPECTED:</br>
     * { "errors": [ { "code": "ERROR_CODE_004", "message": "date is invalid, must be format yyyy/MM/dd",
     * "item": "startDate" } ] }
     */
    @Test
    public void registerTodo_whenEndDateIsInvalidDateTime_thenStatus400() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setWorkName("Study CI/CD");
        todoRequest.setStartDate("2020/06/22");
        todoRequest.setEndDate("123");
        todoRequest.setStatus(1);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(todoRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/est/rouge/todos", entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"errors\":[{\"code\":\"ERROR_CODE_004\",\"message\":\"date is invalid, must be format yyyy/MM/dd\",\"item\":\"endDate\"}]}";

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }
}
