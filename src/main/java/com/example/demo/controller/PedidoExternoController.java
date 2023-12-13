package com.example.demo.controller;

import com.example.demo.model.PedidoExterno;
import com.example.demo.model.Topicos;
import com.example.demo.service.PedidoExternoKafkaService;
import com.example.demo.service.PedidoSqsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;

@RequestMapping("/ext/pedidos")
@RestController
@AllArgsConstructor
public class PedidoExternoController {
    private PedidoExternoKafkaService pedidoExternoKafkaService;
    private PedidoSqsService pedidoSqsService;
    @PostMapping("/")
    public PedidoExterno enviarPedidoExternoKafka(@RequestBody PedidoExterno pedidoExterno){
        pedidoExternoKafkaService.send(String.valueOf(Topicos.PEDIDOS_EXTERNOS),pedidoExterno);
        return pedidoExterno;
    }
    @PostMapping("/sqs/{$queueName}")
    public String enviarPedidoDesdeSQSHaciaKafka(@PathVariable String queueName){
        List<Message> awsSqsMessages = pedidoSqsService.receiveMessagesFromQueue(queueName, 10, 10);
        return pedidoExternoKafkaService.sendAwsListMessagesToKafka(awsSqsMessages, String.valueOf(Topicos.PEDIDO_QUEUE));
    }
}
