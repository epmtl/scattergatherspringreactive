package epmtl.github.connector.impl;

import epmtl.github.connector.api.DataConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import javax.annotation.PostConstruct;

@SuppressWarnings("unused")
@Service
public class WebApiDataConnector<T> implements DataConnector<T> {

    private WebClient webClientInternalCall;

    @PostConstruct
    void postConstruct() {
        String internalBaseUrl = "http://localhost:8090";
        webClientInternalCall = WebClient.builder().baseUrl(internalBaseUrl).build();
    }

    // Note: unsafe function
    @SuppressWarnings("unchecked")
    @Override
    public Flux<T> getData(String PackageName, String className) {
        Class dataClass;
        try {
            dataClass = Class.forName(PackageName + className);
            if (this.webClientInternalCall != null) {
                System.out.println("getting Data for: " + className);
                return this.webClientInternalCall
                        .get()
                        .uri("/" + className.toLowerCase())
                        .retrieve()
                        .bodyToFlux(dataClass);

            } else {
                return Flux.empty();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return Flux.empty();
        }
    }

}
