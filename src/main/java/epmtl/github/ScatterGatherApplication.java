package epmtl.github;

import epmtl.github.service.SCService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SuppressWarnings("unused")
@SpringBootApplication
public class ScatterGatherApplication {

    public static final Logger logger = LoggerFactory.getLogger(ScatterGatherApplication.class);

    @Autowired
    SCService dataService;

	public static void main(String[] args) {
		SpringApplication.run(ScatterGatherApplication.class, args);
	}
}
