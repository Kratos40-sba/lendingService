package com.loanlander.lending_engine.application.service;


import com.loanlander.lending_engine.domain.expection.UserNotFoundExeption;
import com.loanlander.lending_engine.domain.model.User;
import com.loanlander.lending_engine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenValidationService {
    private final UserRepository userRepository ;
    private final String securityContextBaseUrl ;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public TokenValidationService(UserRepository userRepository,@Value("${security.baseurl}") String securityContextBaseUrl) {
        this.userRepository = userRepository;
        this.securityContextBaseUrl = securityContextBaseUrl;
    }
    public User validateTokenAndGetUser(final String token){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION , token);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(securityContextBaseUrl+"/user/validate", HttpMethod.POST
        , httpEntity,String.class
        );
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return userRepository.findById(responseEntity.getBody()).orElseThrow(()->new UserNotFoundExeption(responseEntity.getBody()));

        }
        throw new RuntimeException("invalide Token");

    }
}
