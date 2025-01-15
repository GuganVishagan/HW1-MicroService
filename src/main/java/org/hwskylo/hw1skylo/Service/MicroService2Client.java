package org.hwskylo.hw1skylo.Service;


import lombok.extern.slf4j.Slf4j;
import org.hwskylo.hw1skylo.Entity.MessageEntity;
import org.springframework.stereotype.Component;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
public class MicroService2Client {


    private final RestTemplate restTemplate = new RestTemplate();

    public boolean sendDataToMicroService2(MessageEntity message) {
        String url = "http://localhost:8081/api/data";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(message.getPayload(), headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            return response.getStatusCode().is2xxSuccessful();
        }
        catch (Exception e) {
            log.error("Failed to deliver message to MicroService-2 : {}",e.getMessage(), e);
            return false;
        }
    }
}
