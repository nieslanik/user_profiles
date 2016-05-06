package cz.muni.fi.pa036.sample;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("cz.muni.fi.pa036.sample")
public class SampleDataConfiguration {
    @Inject
    private SampleDataLoader loader;

    @PostConstruct
    public void createSampleData() {
        loader.createSampleData();
    }
}
