package com.stackroute.muzix.seeder;



import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.repository.MuzixRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Configuration
@PropertySource("classpath:application.properties")

@Component
public class DataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private MuzixRepository muzixRepository;

    @Value("${trackthree}")
    private int thirdTrack;
    @Value("${trackthreename}")
    private String thirdTrackName;
    @Value("${trackthreecomment}")
    private String thirdTrackComment;

    @Autowired
    public DataLoader(MuzixRepository muzixRepository){
        this.muzixRepository = muzixRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading data via CLR");


        Muzix track3 = new Muzix();
        track3.setId(thirdTrack);
        track3.setName(thirdTrackName);
        track3.setComments(thirdTrackComment);
        muzixRepository.save(track3);


    }
}
