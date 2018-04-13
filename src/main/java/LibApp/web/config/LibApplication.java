package LibApp.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("LibApp")
public class LibApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibApplication.class);
    }

}
