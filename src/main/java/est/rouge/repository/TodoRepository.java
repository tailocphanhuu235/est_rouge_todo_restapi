package est.rouge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import est.rouge.entity.Todo;

/**
 * Class todo repository
 * 
 * @author tailocphanhuu
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    /**
     * Get max value of id in Todo table
     * 
     * @return max of id
     */
    public Todo findTopByOrderByIdDesc();

    /**
     * Get min value of id in Todo table
     * 
     * @return max of id
     */
    public Todo findTopByOrderByIdAsc();
}
