package com.acme;

import org.apache.camel.builder.RouteBuilder;

public class RouteInstanceOne extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        templatedRoute("myRouteTemplate")
                .parameter("message", "hello from route 1")
                .routeId("routeInstanceOne");
    }
}
