package est.rouge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Class application
 * 
 * @author tailocphanhuu
 */
@SpringBootApplication
public class TodoRestApiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TodoRestApiApplication.class);
    }

    /**
     * Main method
     * 
     * @param args
     *            array of String type
     */
    public static void main(String[] args) {
        SpringApplication.run(TodoRestApiApplication.class, args);
    }

}
