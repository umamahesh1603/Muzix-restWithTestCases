package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.repository.MuzixRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MuzixServiceImplTest {

    @Mock
    MuzixRepository muzixRepository;

    @InjectMocks
    MuzixServiceImpl serviceImpl;

    private Muzix muzix;
    List<Muzix> musicList;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        muzix=new Muzix(3,"blowin in the wind","Nice song");
    }

    @Test
    public void testSaveByName() throws TrackAlreadyExistsException {

        Mockito.when(muzixRepository.save(muzix)).thenReturn(muzix);
        Muzix  expectedList=serviceImpl.saveTrack(muzix);
        assertEquals(muzix,expectedList);
    }

    @Test
    public void getAllTrackTest()
    {
        List<Muzix> list = new ArrayList<Muzix>();
        Muzix empOne = new Muzix(1, "jude", "John");
        Muzix empTwo = new Muzix(2, "Alex", "kolenchiski");
        Muzix empThree = new Muzix(3, "Steve", "Waugh");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(muzixRepository.findAll()).thenReturn(list);

        //test
        List<Muzix> trackList = serviceImpl.getAllTracks();

       // assertEquals(3, trackList.size());
        assertEquals(list,trackList);
    }

//    @Test
//    public void deleteTrack() throws TrackNotFoundException {
//      List <Muzix> muzix = new ArrayList<Muzix>();
//              Muzix trackOne=new Muzix(1, "hey jude","yeah");
////
//              muzix.add(trackOne);
//        when(muzixRepository.deleteById(1)).thenReturn(muzix).then(null);
//
//        List <Muzix> muzix1 =serviceImpl.getAllTracks();
////        muzix=new Muzix(1,"blowin in the wind","Nice song");
////        Mockito.when(muzixRepository.deleteById(1)).the‌​nReturn(null);
//
////        boolean result=serviceImpl.findByName("hey jude");
//        assertEquals(muzix1,muzix);
//    }

//

    @Test
    public void testRemoveTrack() {
        //Muzix muzix = new Muzix(1, "hey jude","yeah");

        List<Muzix> muzixs = Arrays.asList(
                new Muzix(1, "dont let me down","nice"));
                when(muzixRepository.deleteById(8)).thenReturn(muzixs);
                
//        doNothing().when(muzixRepository.findById(anyInt()));
//        doNothing().when(muzixRepository.deleteById(anyInt()));

        verify(muzixRepository, times(0)).deleteById(muzix.getId());

    }

    @Test
    public void findByNameTest(){
        List<Muzix> muzixs = Arrays.asList(
                new Muzix(1, "californication","ok"));

        when(muzixRepository.findByName("californication")).thenReturn(muzixs);

        List<Muzix> expectedList=serviceImpl.findByName("californication");

        assertEquals(expectedList,muzixs);

    }

}