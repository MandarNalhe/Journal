package com.mandar.Journal.services;

import com.mandar.Journal.appCache.AppCache;
import com.mandar.Journal.entity.Response;
import com.mandar.Journal.entity.User;
import com.mandar.Journal.repository.ConfigRepository;
import com.mandar.Journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Value("${quote.api.key}")
    private String apiKey ;
    @Value("${quote.api.value}")
    private String apiKeyValue;
    @Autowired
    AppCache appCache;
    @Autowired
    RestTemplate restTemplate;
    @Transactional
    public String createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set(apiKey,apiKeyValue);
        HttpEntity<String> entity = new HttpEntity<String>("",headers);
        ResponseEntity<Response[]> res = restTemplate.exchange(appCache.getData("api"), HttpMethod.GET,entity,Response[].class);
        List<Response> responses = Arrays.asList(Objects.requireNonNull(res.getBody()));
        return "Hi "+user.getUsername()+"\n Here's a Quote for you: "+responses.get(0).getQuote()+"\n By : "+responses.get(0).getAuthor()+"\n Today is Monday";
    }

    public boolean updateUser(User user, String username) {
        User userInDB = userRepo.findByUsername(username);
        userInDB.setUsername(user.getUsername());
        userInDB.setPassword(user.getPassword());
        createUser(userInDB);
        return true;
    }

    public boolean deleteUser(String username) {
        userRepo.deleteByUsername(username);
        return true;
    }

}
