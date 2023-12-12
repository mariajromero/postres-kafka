package com.example.demo;

import com.example.demo.model.PedidoExterno;
import com.example.demo.service.PedidoExternoKafkaService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PostresProducerApplication  {


	public static void main(String[] args) {
		SpringApplication.run(PostresProducerApplication.class, args);
	}




}
