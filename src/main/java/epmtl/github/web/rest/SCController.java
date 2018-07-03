package epmtl.github.web.rest;


import epmtl.github.resource.outbound.Data;
import epmtl.github.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@SuppressWarnings("unused")
@RestController
public class SCController {

    @Autowired
    private SCService scatterService;

    @CrossOrigin
    @RequestMapping("/data")
    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Data> getData(){
        return scatterService.getData();
    }
}
