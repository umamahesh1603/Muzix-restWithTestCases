package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Muzix;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



    @Repository
    public interface MuzixRepository extends CrudRepository<Muzix, Integer> {

        @Query
        public List<Muzix> findByNameAndComments(String name, String command);

        @Query
        public List<Muzix> findByName(String name);

        @Query
        public List<Muzix> deleteById(int id);


    }


