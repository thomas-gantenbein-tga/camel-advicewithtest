package com.acme;

import org.apache.camel.builder.RouteBuilder;

public class RouteInstanceTwo extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        templatedRoute("myRouteTemplate")
                .parameter("message", "hello from route 2")
                .routeId("routeInstanceTwo");
    }
}
