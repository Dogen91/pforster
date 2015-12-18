package ch.pforster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import ch.pforster.common.config.WebAppConfig;

@SpringBootApplication
@Import({ WebAppConfig.class })
public class PforsterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PforsterApplication.class, args);
    }
}
