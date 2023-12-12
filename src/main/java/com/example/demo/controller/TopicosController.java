package com.example.demo.controller;

import com.example.demo.model.PedidoExterno;
import com.example.demo.model.Topicos;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ext/topicos")
@RestController
public class TopicosController {
    @GetMapping("/")
    public Topicos[] enviarPedidoExternoKafka(){
        return Topicos.values();
    }
}
