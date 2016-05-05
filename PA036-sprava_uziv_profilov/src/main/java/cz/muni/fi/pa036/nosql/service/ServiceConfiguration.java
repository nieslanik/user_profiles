package cz.muni.fi.pa036.nosql.service;

import cz.muni.fi.pa036.nosq.entities.Account;
import cz.muni.fi.pa036.nosq.entities.Restaurant;
import cz.muni.fi.pa036.nosq.entities.Review;
import cz.muni.fi.pa036.nosql.dto.AccountDTO;
import cz.muni.fi.pa036.nosql.dto.RestaurantDTO;
import cz.muni.fi.pa036.nosql.dto.ReviewDTO;
import javax.persistence.PersistenceContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
