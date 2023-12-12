package com.example.demo.service;

import com.example.demo.model.PedidoExterno;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Slf4j
@AllArgsConstructor
@Service
public class PedidoExternoKafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    public void send(String topico, PedidoExterno pedidoExterno){
        var future= kafkaTemplate.send(topico,pedidoExterno.toString());
        future.whenComplete((resultadoEnvio, excepcion)->{
            if(excepcion != null){
                log.error(excepcion.getMessage());
                future.completeExceptionally(excepcion);
            } else {
                log.info("Evento Externo enviado al topico -> " + topico + " en Kafka " + pedidoExterno);
                future.complete(resultadoEnvio);

            }
        });
    }
    public void send(String topico,Integer key, PedidoExterno pedidoExterno){
        var future= kafkaTemplate.send(topico,key.toString(), pedidoExterno.toString());
        future.whenComplete((resultadoEnvio, excepcion)->{
            if(excepcion != null){
                log.error(excepcion.getMessage());
                future.completeExceptionally(excepcion);
            } else {
                log.info("Evento Externo enviado al topico -> " + topico + " en Kafka " + pedidoExterno);
                future.complete(resultadoEnvio);

            }
        });
    }

}
