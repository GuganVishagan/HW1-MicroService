package org.hwskylo.hw1skylo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "org.hwskylo.hw1skylo")
public class Hw1SkyloApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw1SkyloApplication.class, args);
    }

}
