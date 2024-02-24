package com.acme;

import org.apache.camel.BeanInject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.test.main.junit5.CamelMainTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@CamelMainTest(mainClass = MyApplication.class)
class ReplaceFromWithAdviceWithTest {

    @BeanInject
    CamelContext context;


    @Test
    void givenAdviceWith_willPass() throws Exception {
        AdviceWith.adviceWith(context, "routeInstanceOne", r ->  {
            r.replaceFromWith("direct:start");
        });
        NotifyBuilder notify = new NotifyBuilder(context)
                .whenCompleted(1).create();
        assertTrue(
                notify.matches(5, TimeUnit.SECONDS), "1 message should be completed"
        );
    }
}
