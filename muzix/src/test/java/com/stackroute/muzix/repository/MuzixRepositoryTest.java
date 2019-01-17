package com.stackroute.muzix.repository;


import com.stackroute.muzix.domain.Muzix;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MuzixRepositoryTest {

    @Autowired
    private MuzixRepository muzixRepository;

    private Muzix muzix;

    @Before
    public void setUp() throws Exception {

        muzix = new Muzix(1, "john", "dev");
    }

    @Test
    public void testRegisterUserSuccess() {

        muzixRepository.save(muzix);
        Optional<Muzix> object = muzixRepository.findById(muzix.getId());
        assertThat(object.equals(muzix));
    }

    @Test
    public void testFindByNameSuccess() {
        muzixRepository.findByName("john");
        Optional<Muzix> object=muzixRepository.findById(muzix.getId());
        assertThat(object.equals(muzix));
    }


}