package org.hwskylo.hw1skylo.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthCheckController {
    @GetMapping("/health")
    public ResponseEntity<String > healthCheck() {
        return ResponseEntity.ok("Microservice-1 is up and running");
    }
}
