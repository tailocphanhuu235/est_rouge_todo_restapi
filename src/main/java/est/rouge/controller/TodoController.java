package est.rouge.controller;

import java.util.List;

import javax.validation.constraints.Digits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import est.rouge.common.constants.Constants;
import est.rouge.common.dto.CommonResponse;
import est.rouge.dto.TodoRequest;
import est.rouge.dto.TodoResponse;
import est.rouge.exception.GlobalException;
import est.rouge.service.TodoService;
import est.rouge.validation.group.GroupHigh;
import est.rouge.validation.group.ValidationGroup;

/**
 * Class controller
 * 
 * @author tailocphanhuu
 */
@RestController
@RequestMapping(Constants.URL_BASE)
@Validated(value = { ValidationGroup.class })
public class TodoController {
    /**
     * Service handle business CRUD
     */
    @Autowired
    TodoService todoService;

    /**
     * Get list all todos
     * 
     * @return ResponseEntity
     * @throws GlobalException
     */
    @GetMapping(value = Constants.URL_TODO)
    public ResponseEntity<?> getListAllTodos(
            @Validated @RequestParam(defaultValue = "0") @Digits(integer = 3, fraction = 0, message = Constants.ERROR_CODE_007, groups = GroupHigh.class) String pageNo,
            @Validated @RequestParam(defaultValue = "5") @Digits(integer = 3, fraction = 0, message = Constants.ERROR_CODE_007, groups = GroupHigh.class) String pageSize,
            @RequestParam(defaultValue = "id") String sortBy) throws GlobalException {
        List<TodoResponse> todoList = todoService.getAll(Integer.valueOf(pageNo), Integer.valueOf(pageSize), sortBy);
        CommonResponse<List<TodoResponse>> commonResponse = new CommonResponse<>();
        commonResponse.setResult(todoList);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Get todo by id
     * 
     * @param id
     *            id of todo object
     * @return ResponseEntity
     * @throws GlobalException
     */
    @GetMapping(value = Constants.URL_TODO + "/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) throws GlobalException {
        TodoResponse todo = todoService.getById(id);

        CommonResponse<TodoResponse> commonResponse = new CommonResponse<>();
        commonResponse.setResult(todo);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Delete todo by id
     * 
     * @param id
     *            id of todo object
     * @return ResponseEntity
     * @throws GlobalException
     */
    @DeleteMapping(value = Constants.URL_TODO + "/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Long id) throws GlobalException {
        TodoResponse todo = todoService.delete(id);

        CommonResponse<TodoResponse> commonResponse = new CommonResponse<>();
        commonResponse.setResult(todo);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Register todo
     * 
     * @param todoRequest
     *            todo request object
     * @return ResponseEntity
     * @throws GlobalException
     */
    @PostMapping(value = Constants.URL_TODO)
    public ResponseEntity<?> registerTodo(
            @Validated(value = { ValidationGroup.class }) @RequestBody TodoRequest todoRequest) throws GlobalException {
        TodoResponse todo = todoService.register(todoRequest);

        CommonResponse<TodoResponse> commonResponse = new CommonResponse<>();
        commonResponse.setResult(todo);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Update todo
     * 
     * @param id
     *            id of todo object
     * @param todoRequest
     *            todo request object
     * @return ResponseEntity
     * @throws GlobalException
     */
    @PutMapping(value = Constants.URL_TODO + "/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Long id,
            @Validated(value = { ValidationGroup.class }) @RequestBody TodoRequest todoRequest) throws GlobalException {
        TodoResponse todo = todoService.update(id, todoRequest);

        CommonResponse<TodoResponse> commonResponse = new CommonResponse<>();
        commonResponse.setResult(todo);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
