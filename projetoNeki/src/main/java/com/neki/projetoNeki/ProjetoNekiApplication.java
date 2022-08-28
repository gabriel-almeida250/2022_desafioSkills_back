package com.neki.projetoNeki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.neki.projetoNeki.configuration.ProjetoNekiApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(ProjetoNekiApiProperty.class)
public class ProjetoNekiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoNekiApplication.class, args);
	}

}
