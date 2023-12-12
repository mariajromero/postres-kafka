package com.example.demo.controller;

import com.example.demo.model.PedidoExterno;
import com.example.demo.model.Topicos;
import com.example.demo.service.PedidoExternoKafkaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ext/pedidos")
@RestController
@AllArgsConstructor
public class PedidoExternoController {
    private PedidoExternoKafkaService pedidoExternoKafkaService;
    @PostMapping("/")
    public PedidoExterno enviarPedidoExternoKafka(@RequestBody PedidoExterno pedidoExterno){
        pedidoExternoKafkaService.send(String.valueOf(Topicos.PEDIDOS_EXTERNOS),pedidoExterno.getSerial(),pedidoExterno);
        return pedidoExterno;
    }
}
