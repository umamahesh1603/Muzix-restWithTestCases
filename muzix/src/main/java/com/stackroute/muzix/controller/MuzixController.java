package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.service.MuzixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping(value = "api/v2")
public class MuzixController {

    @Autowired
    private MuzixService muzixService;

    // Autowired must be on constructor
    public MuzixController(MuzixService muzixService)
    {
        this.muzixService=muzixService;

    }

    @PostMapping("track")
    public ResponseEntity<?> saveUser(@RequestBody Muzix muzix) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;

        Muzix user1 = muzixService.saveTrack(muzix);
        responseEntity = new ResponseEntity<Muzix>(user1, HttpStatus.CREATED);

        return responseEntity;
    }


    @GetMapping("tracks")

    public ResponseEntity<List<Muzix>> listOfUsers() {

        List<Muzix> allUsers = muzixService.getAllTracks();

        return new ResponseEntity<List<Muzix>>(allUsers, HttpStatus.OK);
    }

        @DeleteMapping("tracks/delete/{id}")
    public ResponseEntity<?>deleteTrack(@PathVariable int id) throws TrackNotFoundException {
        ResponseEntity responseEntity;

        muzixService.deleteById(id);
        responseEntity=new ResponseEntity<String>("succesfully deleted", HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("tracks/update/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody Muzix muzix, @PathVariable int id) throws TrackNotFoundException{
        ResponseEntity responseEntity;

        muzix.setId(id);

        muzixService.save(muzix);

        responseEntity= ResponseEntity.noContent().build();
        return responseEntity;
    }




    @GetMapping("tracks/findtrack/{name}")
    public ResponseEntity<List<Muzix>> findUser(@PathVariable("name") String name) {

        List<Muzix> answer = muzixService.findByName(name);


        return new ResponseEntity<>(answer, HttpStatus.OK);

    }






    }



