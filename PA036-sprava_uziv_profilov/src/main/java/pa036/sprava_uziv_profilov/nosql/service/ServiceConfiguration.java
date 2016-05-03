package pa036.sprava_uziv_profilov.nosql.service;

import javax.persistence.PersistenceContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pa036.profiles.entity.Restaurant;
import pa036.profiles.entity.Review;
import pa036.sprava_uziv_profilov.nosql.dto.AccountDTO;
import pa036.sprava_uziv_profilov.nosql.dto.RestaurantDTO;
import pa036.sprava_uziv_profilov.nosql.dto.ReviewDTO;
import pa036.sprava_uziv_profilov.nosql.entities.Account;

/**
 * @author xnieslan
 */
@Configuration
@Import(PersistenceContext.class)
@ComponentScan(basePackageClasses={AccountServiceImpl.class, RestaurantServiceImpl.class})
//@ComponentScan(basePackages = "cz.muni.pa165")
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {

        @Override
        protected void configure() {
            mapping(Restaurant.class, RestaurantDTO.class);
            mapping(Account.class, AccountDTO.class);
            mapping(Review.class, ReviewDTO.class);
        }
    }

}
