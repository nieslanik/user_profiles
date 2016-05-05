package cz.muni.fi.pa165.sample;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.muni.fi.pa165.config.ServiceConfiguration;

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan("cz.muni.fi.pa036.sample")
public class SampleDataConfiguration {
    @Inject
    private SampleDataLoader loader;

    @PostConstruct
    public void createSampleData() {
        loader.createSampleData();
    }
}
