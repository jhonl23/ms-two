package com.intercorp.mstwo.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.mstwo.dto.RootDTO;
import com.intercorp.mstwo.kafka.producer.EventDataModifierProducer;
import com.intercorp.mstwo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EventBatchConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventDataModifierProducer eventDataModifierProducer;

    @KafkaListener(topics = "${spring.kafka.topic2}")
    public void listenForEventBatch(String message) throws Exception {

        try {
            /** STEP (8) **/
            RootDTO rootDTO = objectMapper.readValue(message, RootDTO.class);

            System.out.println("MENSAJE RECIBIDO EN MS-TWO: ");
            System.out.println(rootDTO);

            /**
             * Agregar dato a la columna last modifier
             */
            rootDTO.setRegEx(StringUtil.createRandomString(20, StringUtil.CHARS_LETTERS));

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            rootDTO.setLast_modified("MICROSERVICES_PROCESS_02_"+dateTimeFormatter.format(LocalDateTime.now()));

            System.out.println("MESSAGE SEND MS-TWO TO MS-BATCH: ");
            System.out.println(rootDTO);

            /**
             * STEP (9)
             * Emitir mensaje a MS-BATCH con lista modificada
             */
            this.sendMessageEventBatchConsumer(rootDTO);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageEventBatchConsumer(RootDTO rootDTO) {
        try {
            eventDataModifierProducer.sendMessage(rootDTO);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
