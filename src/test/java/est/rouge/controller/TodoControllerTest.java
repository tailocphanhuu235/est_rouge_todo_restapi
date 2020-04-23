package est.rouge.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import est.rouge.dto.TodoRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class TodoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * JdbcTemplate
     */
    private static JdbcTemplate jdbcTemplate;

    @BeforeClass
    public static void setUpBeforeClass() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/todo_app?useSSL=false&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("tailocphanhuu");
        dataSource.setPassword("tailocphanhuu");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Before
    public void setUp() throws Exception {
        // Delete database
        jdbcTemplate.update("TRUNCATE TABLE todo_app.todo");
        // Init 10 record
        init10Record();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Init 10 record
     */
    private void init10Record() {
        jdbcTemplate.update(
                "INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('1', 'Study Flutter', '2020/04/20', '2020/04/25', '0');");
        jdbcTemplate.update(
                "INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('2', 'Study React Native', '2020/04/22', '2020/04/30', '1');");
        jdbcTemplate.update(
                "INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('3', 'Study JavaScript', '2020/05/22', '2020/05/30', '0');");
        jdbcTemplate.update(
                "INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('4', 'Study AWS', '2020/05/22', '2020/06/30', '2');");
        jdbcTemplate.update(
                "INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('5', 'Study Spring Cloud', '2020/06/22', '2020/08/30', '0');");
        jdbcTemplate.update(
                "INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('6', 'Study Spring Security', '2020/06/22', '2020/08/30', '2');");
        jdbcTemplate.update(
                "INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('7', 'Study HTML', '2020/06/22', '2020/08/30', '2');");
        jdbcTemplate.update(
                "INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('8', 'Study CSS', '2020/06/22', '2020/08/20', '1');");
        jdbcTemplate.update(
                "INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('9', 'Study PL SQL', '2020/07/22', '2020/08/11', '1');");
        jdbcTemplate.update(
                "INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('10', 'Study Hadoop', '2020/07/22', '2020/08/30', '1');");
    }

    /**
     * API: POST: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * { "work_name": null, "startDate": "2020/06/22", "endDate": "2020/08/30",
     * "status": 1 } </br>
     * EXPECTED:</br>
     * HTTP status code = 400 </br>
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
     * { "work_name": "", "startDate": "2020/06/22", "endDate": "2020/08/30",
     * "status": 1 } </br>
     * EXPECTED:</br>
     * HTTP status code = 400 </br>
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
     * HTTP status code = 400 </br>
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
     * HTTP status code = 400 </br>
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
     * HTTP status code = 400 </br>
     * { "errors": [ { "code": "ERROR_CODE_004", "message": "date is invalid, must
     * be format yyyy/MM/dd", "item": "startDate" } ] }
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
     * HTTP status code = 400 </br>
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
     * HTTP status code = 400 </br>
     * { "errors": [ { "code": "ERROR_CODE_004", "message": "date is invalid, must
     * be format yyyy/MM/dd", "item": "startDate" } ] }
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

    /**
     * API: POST: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * { "work_name": "Study CI/CD", "startDate": "2020/06/22", "endDate":
     * "2020/08/30", "status": null } </br>
     * EXPECTED:</br>
     * HTTP status code = 400 </br>
     * { "errors": [ { "code": "ERROR_CODE_003", "message": "must not be null",
     * "item": "status" } ] }
     */
    @Test
    public void registerTodo_whenStatusIsNull_thenStatus400() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setWorkName("Study CI/CD");
        todoRequest.setStartDate("2020/06/22");
        todoRequest.setEndDate("2020/08/30");
        todoRequest.setStatus(null);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(todoRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/est/rouge/todos", entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"errors\":[{\"code\":\"ERROR_CODE_003\",\"message\":\"must not be null\",\"item\":\"status\"}]}";

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: POST: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * { "work_name": "Study CI/CD", "startDate": "2020/06/22", "endDate":
     * "2020/08/30", "status": 3 } </br>
     * EXPECTED:</br>
     * HTTP status code = 400 </br>
     * { "errors": [ { "code": "ERROR_CODE_006", "message": "must be between 0 and
     * 2", "item": "status" } ] }
     */
    @Test
    public void registerTodo_whenStatusHaveRangeInvalid_thenStatus400() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setWorkName("Study CI/CD");
        todoRequest.setStartDate("2020/06/22");
        todoRequest.setEndDate("2020/08/30");
        todoRequest.setStatus(3); // Status (0: Planning, 1: Doing, 2: Complete) - Status = 3 is Invalid

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(todoRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/est/rouge/todos", entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"errors\":[{\"code\":\"ERROR_CODE_006\",\"message\":\"must be between 0 and 2\",\"item\":\"status\"}]}";

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: POST: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * { "work_name": "Study CI/CD", "startDate": "2020/06/22", "endDate":
     * "2020/08/30", "status": 1 } </br>
     * EXPECTED:</br>
     * HTTP status code = 200 </br>
     * { "result": { "id": 11, "workName": "Study CI/CD", "startDate": "2020/06/22",
     * "endDate": "2020/08/30", "status": 1 } }
     */
    @Test
    public void registerTodo_whenSuccess_thenStatus200() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setWorkName("Study CI/CD");
        todoRequest.setStartDate("2020/06/22");
        todoRequest.setEndDate("2020/08/30");
        todoRequest.setStatus(1);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(todoRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/est/rouge/todos", entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"result\":{\"id\":11,\"workName\":\"Study CI/CD\",\"startDate\":\"2020/06/22\",\"endDate\":\"2020/08/30\",\"status\":1}}";

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: POST: http://localhost:8080/est/rouge/todos</br>
     * TEST BY DEBUG THEN STOP MYSQL DATABASE </br>
     * INPUT: </br>
     * { "work_name": "Study CI/CD", "startDate": "2020/06/22", "endDate":
     * "2020/08/30", "status": 1 } </br>
     * EXPECTED:</br>
     * HTTP status code = 500 </br>
     * { "errors": [ { "code": "ERROR_CODE_500", "message": "500 Internal Server
     * Error", "item": null } ] }
     */
    @Test
    public void registerTodo_whenConnectDBFailed_thenStatus500() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setWorkName("Study CI/CD");
        todoRequest.setStartDate("2020/06/22");
        todoRequest.setEndDate("2020/08/30");
        todoRequest.setStatus(1);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(todoRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/est/rouge/todos", entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"errors\":[{\"code\":\"ERROR_CODE_500\",\"message\":\"500 Internal Server\",\"item\":\"null\"}]}";

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: GET: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * http://localhost:8080/est/rouge/todos?pageNo=10&pageSize=2&sortBy=status
     * </br>
     * EXPECTED:</br>
     * HTTP status code = 200 </br>
     * { "result": [] }
     */
    @Test
    public void getListAllTodos_whenSuccessWithFullParameterAndDataNotExist_thenStatus200() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/est/rouge/todos?pageNo=10&pageSize=2&sortBy=status",
                HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"result\":[]}";

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: GET: http://localhost:8080/est/rouge/todos</br>
     * TEST BY DEBUG THEN STOP MYSQL DATABASE </br>
     * INPUT: </br>
     * http://localhost:8080/est/rouge/todos?pageNo=10&pageSize=2&sortBy=status
     * </br>
     * EXPECTED:</br>
     * HTTP status code = 500 </br>
     * { "errors": [ { "code": "ERROR_CODE_500", "message": "500 Internal Server
     * Error", "item": null } ] }
     */
    @Test
    public void getListAllTodos_whenConnectDBFailed_thenStatus500() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/est/rouge/todos?pageNo=10&pageSize=2&sortBy=status",
                HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"errors\":[{\"code\":\"ERROR_CODE_500\",\"message\":\"500 Internal Server\",\"item\":\"null\"}]}";

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: GET: http://localhost:8080/est/rouge/todos</br>
     * INPUT: </br>
     * http://localhost:8080/est/rouge/todos?pageNo=0&pageSize=2&sortBy=status </br>
     * EXPECTED:</br>
     * HTTP status code = 200 </br>
     * { "result": [ { "id": 1, "workName": "Study Flutter", "startDate":
     * "2020/04/20", "endDate": "2020/04/25", "status": 0 }, { "id": 3, "workName":
     * "Study JavaScript", "startDate": "2020/05/22", "endDate": "2020/05/30",
     * "status": 0 } ] }
     */
    @Test
    public void getListAllTodos_whenSuccessWithFullParameterAndHaveResult_thenStatus200() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/est/rouge/todos?pageNo=0&pageSize=2&sortBy=status",
                HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"result\":[{\"id\":1,\"workName\":\"Study Flutter\",\"startDate\":\"2020/04/20\",\"endDate\":\"2020/04/25\",\"status\":0},{\"id\":3,\"workName\":\"Study JavaScript\",\"startDate\":\"2020/05/22\",\"endDate\":\"2020/05/30\",\"status\":0}]}";

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    /**
     * API: GET: http://localhost:8080/est/rouge/todos</br>
     * TEST BY DEBUG THEN STOP MYSQL DATABASE </br>
     * INPUT: </br>
     * http://localhost:8080/est/rouge/todos?pageNo=abc&pageSize=abc&sortBy=status
     * </br>
     * EXPECTED:</br>
     * HTTP status code = 400 </br>
     * { "errors": [ { "code": "ERROR_CODE_007", "message": "must be number",
     * "item": "pageNo" }, { "code": "ERROR_CODE_007", "message": "must be number",
     * "item": "pageSize" } ] }
     */
    @Test
    public void getListAllTodos_whenPageNoAndPageSizeIsInValidNumber_thenStatus400() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TodoRequest> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "/est/rouge/todos?pageNo=abc&pageSize=abc&sortBy=status", HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());

        String expectedStr = "{\"errors\":[{\"code\":\"ERROR_CODE_007\",\"message\":\"must be number\",\"item\":\"pageNo\"},{\"code\":\"ERROR_CODE_007\",\"message\":\"must be number\",\"item\":\"pageSize\"}]}";

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedStr, response.getBody());
    }

    // Test getTodoById
    // Test deleteTodoById
    // Test updateTodo
}
