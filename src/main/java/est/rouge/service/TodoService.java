package est.rouge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import est.rouge.common.constants.Constants;
import est.rouge.common.utils.Utils;
import est.rouge.dto.TodoRequest;
import est.rouge.dto.TodoResponse;
import est.rouge.entity.Todo;
import est.rouge.exception.Http500Exception;
import est.rouge.repository.TodoRepository;

/**
 * Class todo service for handle business
 * 
 * @author tailocphanhuu
 */
@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    /**
     * Get list all todos
     * 
     * @return list todo response
     * @throws Http500Exception
     */
    public List<TodoResponse> getAll(Integer pageNo, Integer pageSize, String sortBy) throws Http500Exception {
        List<TodoResponse> response = new ArrayList<TodoResponse>();
        try {
            Page<Todo> page = todoRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending()));

            if (page.hasContent()) {
                List<Todo> todoList = page.getContent();
                for (Todo todo : todoList) {
                    TodoResponse res = new TodoResponse();
                    res.setId(todo.getId());
                    res.setWorkName(todo.getWorkName());
                    res.setStartDate(Utils.formatDate_yyyyMMdd(todo.getStartDate()));
                    res.setEndDate(Utils.formatDate_yyyyMMdd(todo.getEndDate()));
                    res.setStatus(todo.getStatus());
                    response.add(res);
                }
            }

        } catch (Exception exception) {
            throw new Http500Exception(Constants.ERROR_CODE_500);
        }

        return response;
    };

    /**
     * Get todo by id
     * 
     * @param id
     *            id of todo object
     * @return todo response
     * @throws Http500Exception
     */
    public TodoResponse getById(long id) throws Http500Exception {
        TodoResponse response = null;
        try {
            // Check object exist
            // Todo todo = todoRepository.getOne(id);
            Optional<Todo> todoExist = todoRepository.findById(id);
            if (todoExist.isPresent()) {
                Todo todo = todoExist.get();
                response = new TodoResponse();
                response.setId(todo.getId());
                response.setWorkName(todo.getWorkName());
                response.setStartDate(Utils.formatDate_yyyyMMdd(todo.getStartDate()));
                response.setEndDate(Utils.formatDate_yyyyMMdd(todo.getEndDate()));
                response.setStatus(todo.getStatus());
            }
        } catch (Exception exception) {
            throw new Http500Exception(Constants.ERROR_CODE_500);
        }

        return response;
    }

    /**
     * Delete todo by id
     * 
     * @param id
     *            id of todo object
     * @return response todo that was deleted
     * @throws Http500Exception
     */
    public TodoResponse delete(long id) throws Http500Exception {
        TodoResponse response = null;
        try {
            // Check exist object
            // Todo todo = todoRepository.getOne(id);
            Optional<Todo> todoExist = todoRepository.findById(id);
            if (todoExist.isPresent()) {
                Todo todo = todoExist.get();
                response = new TodoResponse();
                response.setId(todo.getId());
                response.setWorkName(todo.getWorkName());
                response.setStartDate(Utils.formatDate_yyyyMMdd(todo.getStartDate()));
                response.setEndDate(Utils.formatDate_yyyyMMdd(todo.getEndDate()));
                response.setStatus(todo.getStatus());
                todoRepository.deleteById(id);
            }
        } catch (Exception exception) {
            throw new Http500Exception(Constants.ERROR_CODE_500);
        }

        return response;
    }

    /**
     * Register todo
     * 
     * @param todoRequest
     *            todo object will register
     * @return response todo was registered
     * @throws Http500Exception
     */
    public TodoResponse register(TodoRequest todoRequest) throws Http500Exception {
        TodoResponse response = null;
        try {
            // Get max value of id in todo table
            Long maxId = todoRepository.findTopByOrderByIdDesc().getId();
            System.out.println("Max value of ID: " + maxId);

            Todo todo = new Todo();
            todo.setId(Long.valueOf(maxId + 1));
            todo.setWorkName(todoRequest.getWorkName());
            todo.setStartDate(Utils.convertDate_yyyyMMdd(todoRequest.getStartDate()));
            todo.setEndDate(Utils.convertDate_yyyyMMdd(todoRequest.getEndDate()));
            todo.setStatus(todoRequest.getStatus());
            todoRepository.save(todo);

            // Set return response of todo object is registered complete
            response = new TodoResponse();
            response.setId(todo.getId());
            response.setWorkName(todo.getWorkName());
            response.setStartDate(Utils.formatDate_yyyyMMdd(todo.getStartDate()));
            response.setEndDate(Utils.formatDate_yyyyMMdd(todo.getEndDate()));
            response.setStatus(todo.getStatus());
        } catch (Exception exception) {
            throw new Http500Exception(Constants.ERROR_CODE_500);
        }

        return response;
    }

    /**
     * Update todo
     * 
     * @param id
     *            id of todo object
     * @param todoRequest
     *            todo object will update
     * @return response todo that was updated
     * @throws Http500Exception
     */
    public TodoResponse update(Long id, TodoRequest todoRequest) throws Http500Exception {
        TodoResponse response = null;
        try {
            // Check exist object
            // Todo todo = todoRepository.getOne(id);
            Optional<Todo> todoExist = todoRepository.findById(id);

            if (todoExist.isPresent()) {
                Todo todo = todoExist.get();
                todo.setWorkName(todoRequest.getWorkName());
                todo.setStartDate(Utils.convertDate_yyyyMMdd(todoRequest.getStartDate()));
                todo.setEndDate(Utils.convertDate_yyyyMMdd(todoRequest.getEndDate()));
                todo.setStatus(todoRequest.getStatus());
                todoRepository.save(todo);

                // Return todo object that is deleted
                response = new TodoResponse();
                response.setId(todo.getId());
                response.setWorkName(todo.getWorkName());
                response.setStartDate(Utils.formatDate_yyyyMMdd(todo.getStartDate()));
                response.setEndDate(Utils.formatDate_yyyyMMdd(todo.getEndDate()));
                response.setStatus(todo.getStatus());
            }
        } catch (Exception exception) {
            throw new Http500Exception(Constants.ERROR_CODE_500);
        }

        return response;
    }
}
