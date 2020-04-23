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
     * { "work_name": null, "start_date": "2020/06/22", "end_date": "2020/08/30",
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
}
