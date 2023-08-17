package com.app.birca;

import com.app.birca.config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({JwtConfig.class})
public class BircaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BircaApplication.class, args);
    }

}
