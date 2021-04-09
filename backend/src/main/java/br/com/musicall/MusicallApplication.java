package br.com.musicall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;


@SpringBootApplication
@EnableFeignClients
@Configuration
@EnableScheduling
public class MusicallApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MusicallApplication.class, args);

    }
}
