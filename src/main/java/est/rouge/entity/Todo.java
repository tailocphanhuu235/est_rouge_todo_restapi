package est.rouge.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Class represents Todo table
 * 
 * @author tailocphanhuu
 */
@Data
@Entity
@Table(name = "todo", schema = "todo_app")
public class Todo {
    /**
     * Primary key
     */
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Work name
     */
    @Column(name = "work_name")
    private String workName;

    /**
     * Start date
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * End date
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * Status (0: Planning, 1: Doing, 2: Complete) 
     */
    @Column(name = "status")
    private Integer status;
}
