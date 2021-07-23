package ua.com.foxminded.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages={"ua.com.foxminded"})
@EnableSwagger2
@EnableOpenApi
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

