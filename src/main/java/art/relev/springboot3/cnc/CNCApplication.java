package art.relev.springboot3.cnc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("art.relev.springboot3.cnc.dao")
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "art.relev.springboot3.cnc.dao.excluded.*"))
public class CNCApplication {
    public static void main(String[] args) {
        SpringApplication.run(CNCApplication.class, args);
    }
}
