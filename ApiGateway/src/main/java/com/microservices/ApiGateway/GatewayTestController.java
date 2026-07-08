package com.microservices.ApiGateway;

import org.springframework.beans.factory.annotation.Autowired;
//import io.micrometer.tracing.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayTestController {

//    @Autowired
//    private Tracer tracer;
//
//    @GetMapping("/gateway-test")
//    public String test() {
//        System.out.println("========================================");
//        System.out.println("TRACER CLASS IS: " + tracer.getClass().getName());
//        System.out.println("========================================");
//        return "Gateway is alive!";
//    }
}