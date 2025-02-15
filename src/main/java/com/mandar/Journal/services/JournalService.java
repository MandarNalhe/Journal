package com.mandar.Journal.services;

import com.mandar.Journal.entity.Journal;
import com.mandar.Journal.entity.User;
import com.mandar.Journal.repository.JournalRepository;
import com.mandar.Journal.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {
    @Autowired
    JournalRepository journalRepo;
    @Autowired
    UserRepository userRepo;
    @Transactional
    public boolean journalEntry(Journal journal, String username) {
        journal.setDate(LocalDate.now());
        User userInDB = userRepo.findByUsername(username);
        try{
            journalRepo.save(journal);
            userInDB.getJournals().add(journal);
            userRepo.save(userInDB);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public List<Journal> getJournals(String username) {
        User userInDB = userRepo.findByUsername(username);
        return userInDB.getJournals();
    }

    public boolean deleteByTitle(String title) {
        Optional<Journal> journal = journalRepo.findByTitle(title);
        if(journal.isPresent()){
            journalRepo.deleteByTitle(title);
            return true;
        }else {
            return false;
        }
    }
    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        Optional<Journal> journal = journalRepo.findById(id);
        User userInDB = userRepo.findByUsername(username);
        if(journal.isPresent() && userInDB.getJournals().contains(journal.get())){
            userInDB.getJournals().remove(journal.get());
            userRepo.save(userInDB);
            journalRepo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public Object getAllJournals() {
        return journalRepo.findAll();
    }

    public void update(ObjectId id, Journal journal, String username) {
        Optional<Journal> inDB = journalRepo.findById(id);
        User user = userRepo.findByUsername(username);
        if(inDB.isPresent() && user.getJournals().contains(inDB.get())){
            user.getJournals().remove(inDB.get());
            Journal temp = inDB.get();
            temp.setBody(journal.getBody());
            temp.setTitle(journal.getTitle());
            temp.setDate(LocalDate.now());
            journalRepo.save(temp);
            user.getJournals().add(temp);
            userRepo.save(user);
        }else{
            throw new RuntimeException();
        }
    }
}
