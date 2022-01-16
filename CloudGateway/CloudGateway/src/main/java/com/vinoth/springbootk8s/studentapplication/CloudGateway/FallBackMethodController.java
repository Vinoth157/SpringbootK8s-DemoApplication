package com.vinoth.springbootk8s.studentapplication.CloudGateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/addressServiceFallBack")
    public String employeeServiceFallBackMethod() {
        return "Address Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/parentServiceFallBack")
    public String projectServiceFallBackMethod() {
        return "Parent Service is taking longer than Expected." +
                " Please try again later";
    }
}
