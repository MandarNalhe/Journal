package com.mandar.Journal.services;


import com.mandar.Journal.entity.Journal;
import com.mandar.Journal.entity.User;
import com.mandar.Journal.repository.UserImplRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    UserImplRepository userRepo;
    @Scheduled(cron="0 9 * * SUN")
    public void mailSender() {
        List<User> users = userRepo.getUserWithSA();
        for (User user : users) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(user.getMail());
            mail.setSubject("Senitment Analysis");
            List<Journal> journals = user.getJournals();
            HashMap<String,Integer> hm = new HashMap<>();
            for(Journal journal : journals){
                hm.put(journal.getSentiment(),hm.getOrDefault(journal.getSentiment(),0)+1);
            }
            String max= "";
            int count =0 ;
            for(Map.Entry<String,Integer> map : hm.entrySet()){
                if(map.getValue()>=count){
                    count = map.getValue();
                    max = map.getKey();
                }
            }
            mail.setText(max);
            javaMailSender.send(mail);
        }
    }



}
