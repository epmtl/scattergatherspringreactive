package epmtl.github.connector.api;

import reactor.core.publisher.Flux;

@SuppressWarnings("unused")
public interface DataConnector<T> {
    Flux<T> getData(String PackageName, String className);
}
