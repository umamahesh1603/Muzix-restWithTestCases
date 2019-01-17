package com.stackroute.muzix.seeder;



import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.repository.MuzixRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
@Configuration
@PropertySource("classpath:application.properties")

@Component
public class DataBaseSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(DataBaseSeeder.class);
    private MuzixRepository muzixRepository;
    @Value("${trackone}")
    private int firstTrack;
    @Value("${trackonename}")
    private String firstTrackName;
    @Value("${trackonecomment}")
    private String firstTrackComment;

    @Autowired
    private Environment env;

    //    @Bean
//    public IApplicationBeanService getService(){
//        return new ApplicationBeansService(env);
//    }
    @Value("${tracktwo}")
    private int secondTrack;
    //
//    private String secondTrackName;
//    private String secondTrackComment;
    @Autowired
    public DataBaseSeeder(MuzixRepository muzixRepository){
        this.muzixRepository = muzixRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        logger.info("Entering Data On Start via refreshed");

        Muzix track1 = new Muzix();
        track1.setName(firstTrackName);
        track1.setComments(firstTrackComment);
        track1.setId(firstTrack);
        muzixRepository.save(track1);


        Muzix track2 = new  Muzix();
        track2.setName(env.getProperty("tracktwoname"));
        track2.setComments(env.getProperty("tracktwocomment"));

        track2.setId(secondTrack);


        muzixRepository.save(track2);

        logger.info("Initial data entered");
    }
}
