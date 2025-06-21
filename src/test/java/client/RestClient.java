package client;

import config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    private final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(ConfigManager.baseUrl())
            .setContentType("application/json")
            .build();

    public RequestSpecification spec() {
        return spec;
    }
}