package fiap;

import fiap.sus.config.DotenvInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SusApplication {

    public static void main(String[] args) {
        DotenvInitializer initializer = new DotenvInitializer();
        SpringApplication.run(SusApplication.class, args);
    }

}
