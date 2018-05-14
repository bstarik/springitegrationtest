package org.boris.springintegration.test.activator.endpoint;

import org.springframework.messaging.Message;

public interface Activator <T>{
    public Message<?> get(Message<String> msg);

}
