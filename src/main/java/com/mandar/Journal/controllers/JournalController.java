package com.mandar.Journal.controllers;

import com.mandar.Journal.entity.Journal;
import com.mandar.Journal.services.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {
    @Autowired
    JournalService journalService;

    @PostMapping()
    public ResponseEntity<Boolean> journalEntry(@RequestBody Journal journal){

        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            boolean ans = journalService.journalEntry(journal,username);
            if(ans)
                return new ResponseEntity<>(true, HttpStatus.CREATED);
            else
                return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping()
    public  ResponseEntity<List<Journal>> getJournals(){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            try{
                List<Journal> res = journalService.getJournals(username);
                return new ResponseEntity<>(res,HttpStatus.OK);
            }catch (Exception e){
                System.out.println(e);
                return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
            }
    }

//    @DeleteMapping("/{title}")
//    public ResponseEntity<Boolean> deleteByTitle(@PathVariable String title){
//        try {
//            boolean ans = journalService.deleteByTitle(title);
//            if(ans)
//                return new ResponseEntity<>(true,HttpStatus.OK);
//            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
//        }catch(Exception e){
//            System.out.println(e);
//            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
//
//        }
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable ObjectId id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        try {
            boolean ans = journalService.deleteById(id,username);
            if(ans)
                return new ResponseEntity<>(true,HttpStatus.OK);
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable ObjectId id, @RequestBody Journal journal){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        try{
            journalService.update(id, journal, username);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllJournals(){
        return new ResponseEntity<>(journalService.getAllJournals(),HttpStatus.OK);
    }
}
