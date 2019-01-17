package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.exception.TrackNotFoundException;

import java.util.List;

public interface MuzixService {


    public Muzix saveTrack(Muzix muzix) throws TrackAlreadyExistsException;

    public List<Muzix> getAllTracks();


    public List<Muzix> findByName(String name);

    public void deleteById(int id) throws TrackNotFoundException;


    public Muzix save(Muzix muzix) throws TrackNotFoundException;
}

