package com.example.demo.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;
@AllArgsConstructor
@Service
public class PedidoSqsService {
    private final AmazonSQS sqs;
    private String getQueueUrl(String queueName){
        return sqs.getQueueUrl(queueName).getQueueUrl();
    }
    public List<Message> receiveMessagesFromQueue(String queueName, Integer maxNumberOfMessages, Integer waitTimeSeconds){
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(getQueueUrl(queueName))
                .withMaxNumberOfMessages(maxNumberOfMessages)
                .withMessageAttributeNames(List.of("All"))
                .withWaitTimeSeconds(waitTimeSeconds);
        return sqs.receiveMessage(receiveMessageRequest).getMessages();
    }

}
