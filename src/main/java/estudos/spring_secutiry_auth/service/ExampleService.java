package estudos.spring_secutiry_auth.service;

import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    public String validate(Boolean isAuthorized) {
        if (isAuthorized)
            return "Authorized";
        else
            return "Not Authorized";
    }
}
