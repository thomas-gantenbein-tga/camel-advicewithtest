package com.acme;

import org.apache.camel.builder.RouteBuilder;

public class RouteTemplate extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        routeTemplate("myRouteTemplate")
                .templateParameter("message")
                .from("timer:atimer")
                .log("{{message}}");
    }
}
