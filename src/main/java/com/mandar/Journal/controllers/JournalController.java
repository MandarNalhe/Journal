package com.mandar.Journal.controllers;

import com.mandar.Journal.entity.Journal;
import com.mandar.Journal.services.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            boolean ans = journalService.journalEntry(journal);
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
            try{
                List<Journal> res = journalService.getJournals();
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
        try {
            boolean ans = journalService.deleteById(id);
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
        try{
            journalService.update(id,journal);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
}
