package com.acme;

import org.apache.camel.BeanInject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.main.MainConfigurationProperties;
import org.apache.camel.test.main.junit5.CamelMainTest;
import org.apache.camel.test.main.junit5.Configure;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@CamelMainTest(replaceRouteFromWith = {"routeInstanceOne=direct:start"})
class ReplaceFromWithAnnotationOnlyOneTemplatedRouteTest {

    @BeanInject
    CamelContext context;

    @BeanInject
    ProducerTemplate producer;

    @Configure
    protected void configure(MainConfigurationProperties configuration) {
        configuration.setRoutesBuilderClasses("com.acme.RouteInstanceOne, com.acme.RouteTemplate");
    }


    @Test
    void givenOnlyOneRoute_willPass() throws Exception {
        NotifyBuilder notify = new NotifyBuilder(context)
                .whenCompleted(1).create();
        producer.sendBody("direct:start", "hello");
        assertTrue(
                notify.matches(5, TimeUnit.SECONDS), "1 message should be completed"
        );
    }
}
