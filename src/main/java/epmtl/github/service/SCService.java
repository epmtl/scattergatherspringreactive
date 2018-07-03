package epmtl.github.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import epmtl.github.connector.api.DataConnector;
import epmtl.github.resource.inbound.Data1;
import epmtl.github.resource.inbound.Data2;
import epmtl.github.resource.outbound.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@SuppressWarnings("unused")
@Service
public class SCService {

    @Autowired
    private DataConnector<Data1> dataConnector1;

    @Autowired
    private DataConnector<Data2> dataConnector2;


    private Data gatherObjects(Data1 d1, Data2 d2) {
        Data data = new Data();
        data.setData1(d1);
        data.setData2(d2);
        // TODO
        ObjectMapper objectMapper = new ObjectMapper();
        String out = "";
        //objectMapper.writeValue(out, d1);
        return data;
    }


    public Flux<Data> getData() {

        String packageName = "epmtl.github.resource.inbound.";
        return Flux.zip(
                (dataConnector1.getData(packageName, "Data1")),
                (dataConnector2.getData(packageName,"Data2")),
                this::gatherObjects);
    }

}
