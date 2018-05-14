package org.boris.springintegration.test.activator.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.http.Http;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {

//    @Autowired
//    WelcomeEndpoint welcomeEndpoint;



    @Bean
    @Qualifier("requestChanel")
    public MessageChannel requestChanel(){
        return MessageChannels.direct().get();
    }


    @Bean
    @Qualifier("outputChanel")
    public MessageChannel outputChanel(){
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow httpInternalServiceFlow() {
        return IntegrationFlows
                .from(Http.inboundGateway("/welcome/{name}")
                        .requestChannel(requestChanel())
                        .replyChannel(outputChanel())
                        .payloadExpression("#pathVariables.name")
                        .requestMapping(r -> r
                                .methods(HttpMethod.GET)
                                .consumes("application/json")
                                .produces("application/json"))).get();

    }


//    @Bean
//    public IntegrationFlow flow() {
//        return IntegrationFlows.from(requestChanel())
//                .handle(httpGate())
//    		.get();
//
//    }




//    @Bean
//    public HttpRequestHandlingMessagingGateway httpGate() {
//        HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
//        RequestMapping requestMapping = new RequestMapping();
//        requestMapping.setMethods(HttpMethod.GET);
//        requestMapping.setPathPatterns("/welcome/{name}");
//        requestMapping.setConsumes("application/json");
//        requestMapping.setProduces("application/json");
//       // gateway.setRequestMapping(requestMapping);
//        //gateway.setRequestChannel(requestChanel());
//        gateway.setReplyChannel(outputChanel());
//        //Expression payloadExpression = new SpelExpressionParser().parseExpression("#pathVariables.name");
//        //gateway.setPayloadExpression(payloadExpression);
//        return gateway;
//    }


//    @ServiceActivator(inputChannel ="requestChanel",outputChannel = "outputChannel")
//    @Bean
//    WelcomeEndpoint welcomeEndpoint(){
//        return new WelcomeEndpoint();
//    }

//    @Bean
//    public IntegrationFlow httpOut() {
//        return IntegrationFlows.from("re")
//                .handle(Http.outboundGateway("http://localhost:8080/")
//                        .charset("UTF-8")
//                        .httpMethod(HttpMethod.POST)
//                        .headerMapper(soapHeaderMapper())
//                        .requestFactory(requestFactory()), e -> e.id("test"))
//                .get();



}
