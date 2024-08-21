package estudos.spring_secutiry_auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudos.spring_secutiry_auth.service.ExampleService;

@RestController
@RequestMapping("example")
public class ExampleController {

    private final ExampleService exampleService;

    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("/")
    public String validate() {
        return exampleService.validate(true);
    }
}
