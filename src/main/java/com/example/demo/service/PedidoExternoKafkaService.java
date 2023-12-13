package com.example.demo.service;

import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.example.demo.model.PedidoAwsSqs;
import com.example.demo.model.PedidoExterno;
import com.example.demo.model.PedidoInterno;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.amazonaws.services.sqs.model.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class PedidoExternoKafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topico,PedidoInterno pedidoInterno){
        var future= kafkaTemplate.send(topico, pedidoInterno.toString());
        future.whenComplete((resultadoEnvio, excepcion)->{
            if(excepcion != null){
                log.error(excepcion.getMessage());
                future.completeExceptionally(excepcion);
            } else {
                log.info("Evento Externo enviado al topico -> " + topico + " en Kafka " + pedidoInterno);
                future.complete(resultadoEnvio);

            }
        });
    }
    public String sendAwsListMessagesToKafka(List<Message> awsSqsMessages, String topico){
        List<PedidoInterno> pedidosInternos = transformPedidoFromAWSSqsToPedidoExterno(awsSqsMessages);
        for(PedidoInterno pedidoInterno: pedidosInternos){
            send(topico, pedidoInterno);
        }
        return "Se han enviado " + pedidosInternos.size() + " eventos desde AWSSqs hacia Kafka";
    }

    private List<PedidoInterno> transformPedidoFromAWSSqsToPedidoExterno(List<Message> awsSqsMessages) {
        List<PedidoInterno> pedidos= new LinkedList<>();
        for(Message message:awsSqsMessages){
            Map<String, MessageAttributeValue> atributosMensaje= message.getMessageAttributes();
            PedidoAwsSqs pedidoAwsSqs= new PedidoAwsSqs(atributosMensaje);
            pedidos.add(pedidoAwsSqs);
        }
        return pedidos;
    }
}
