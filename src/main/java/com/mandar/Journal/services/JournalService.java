package com.mandar.Journal.services;

import com.mandar.Journal.entity.Journal;
import com.mandar.Journal.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {
    @Autowired
    JournalRepository journalRepo;

    public boolean journalEntry(Journal journal) {
        journal.setDate(LocalDate.now());
        try{

            journalRepo.save(journal);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public List<Journal> getJournals() {
        return journalRepo.findAll();
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

    public boolean deleteById(ObjectId id) {
        Optional<Journal> journal = journalRepo.findById(id);
        if(journal.isPresent()){
            journalRepo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public void update(ObjectId id, Journal journal) {
        Optional<Journal> inDB = journalRepo.findById(id);
        if(inDB.isPresent()){
            Journal temp =inDB.get();
            temp.setBody(journal.getBody());
            temp.setTitle(journal.getTitle());
            journalEntry(temp);
        }else{
            throw new RuntimeException();
        }
    }
}
