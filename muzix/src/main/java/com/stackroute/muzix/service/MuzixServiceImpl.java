package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MuzixServiceImpl implements MuzixService {

    private MuzixRepository muzixRepository;

    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository){

        this.muzixRepository=muzixRepository;
    }


    public void setUserRepository(MuzixRepository muzixRepository)

    {
        this.muzixRepository = muzixRepository;
    }

    @Override
    public Muzix saveTrack(Muzix muzix) throws TrackAlreadyExistsException{

        if (muzixRepository.existsById(muzix.getId())){
            throw new TrackAlreadyExistsException("Track already exists");
        }
        Muzix user1= muzixRepository.save(muzix);
        if (user1==null){
            throw new TrackAlreadyExistsException("Track already exists");
        }
        return user1;

    }


    @Override
    public List<Muzix> getAllTracks() {
        List<Muzix> muzixList = (List<Muzix>) muzixRepository.findAll();
        return muzixList;
    }

    @Override
    public void deleteById(int id) throws TrackNotFoundException{
        if (muzixRepository.existsById(id)){
            muzixRepository.deleteById(id);
        }
        throw new TrackNotFoundException(" Track id not found");
    }

    @Override
    public Muzix save(Muzix muzix) throws TrackNotFoundException {
        if (muzixRepository.existsById(muzix.getId())){
            Muzix user2= muzixRepository.save(muzix);
            return user2;
        }
        throw new TrackNotFoundException("Track comment not found");

    }


    @Override
    public List<Muzix> findByName(String name) {
        List<Muzix> userListFound = (List<Muzix>) muzixRepository.findByName(name);
        return userListFound;
    }

}
