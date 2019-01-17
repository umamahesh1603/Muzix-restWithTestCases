package com.stackroute.muzix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.service.MuzixService;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MuzixController.class)
public class MuzixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MuzixService muzixService;

    @InjectMocks
    private MuzixController muzixController;


    Muzix muzix;
    List<Muzix> musicList;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(muzixController)
                .build();

        muzix = new Muzix();
        muzix.setId(123);
        muzix.setName("blowin in the wind");
        muzix.setComments("Nice song");

    }

    @Test
    public void saveUser() throws Exception {

        Mockito.when(muzixService.saveTrack(muzix)).thenReturn(muzix);

        mockMvc.perform(post("/api/v2/track")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(muzix)))
                .andExpect(status().isCreated());

        verify(muzixService,Mockito.times(1)).saveTrack(Mockito.any(Muzix.class));
        verifyNoMoreInteractions(muzixService);
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @Test
    public void test_get_all_success() throws Exception {
        List<Muzix> muzixs = Arrays.asList(
                new Muzix(1, "dont let me down","nice"),
                new Muzix(2, "californication","wow"));
        when(muzixService.getAllTracks()).thenReturn(muzixs);
        mockMvc.perform(get("/api/v2/tracks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("dont let me down")))
                .andExpect(jsonPath("$[0].comments", is("nice")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("californication")))
                .andExpect(jsonPath("$[1].comments", is("wow")));
        verify(muzixService, times(1)).getAllTracks();
        verifyNoMoreInteractions(muzixService);
    }

    @Test
    public void test_delete_user_success() throws Exception {
        Muzix muzix = new Muzix(1, "hey jude","yeah");


        doNothing().when(muzixService).deleteById(muzix.getId());

        mockMvc.perform(
                delete("/api/v2/tracks/delete/{id}", muzix.getId()))
                .andExpect(status().isOk());


        verify(muzixService, times(1)).deleteById(muzix.getId());
        verifyNoMoreInteractions(muzixService);
    }

//    @Test
//    public void test_update_user_success() throws Exception {
//        Muzix track = new Muzix();
//        track.setName("firstTrack1");
//        track.setComments("firstComment1");
//        track.setId(1);
//
//        String comment = track.getComments();
//        when(muzixService.save(track)
//        mockMvc.perform(
//                patch("/api/v2/tracks/update/{id}",1)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(muzix))
//                        .andExpect(status().isOk());
//        verify(muzixService, times(1)).save(track);
//        verifyNoMoreInteractions(muzixService);
//    }


    @Test
    public void test_get_by_name_success() throws Exception {
        List<Muzix> muzixs =Arrays.asList(
                new Muzix(1, "californication","ok"));

        when(muzixService.findByName("californication")).thenReturn(muzixs);

        mockMvc.perform(get("/api/v2/tracks/findtrack/{name}", "californication"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("californication")))
                .andExpect(jsonPath("$[0].comments", is("ok")));

        verify(muzixService, times(1)).findByName("californication");
        verifyNoMoreInteractions(muzixService);
    }

}

