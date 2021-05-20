package com.example.movieservice.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;
import org.apache.activemq.RedeliveryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Session;

/**
 * @author bkariuki
 */
@Configuration
public class GlobalQueueSettings {

    ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");


    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory
                = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(cf);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        RedeliveryPolicy queuePolicy = new RedeliveryPolicy();
        queuePolicy.setMaximumRedeliveries(-1);
        queuePolicy.setInitialRedeliveryDelay(5000);
        queuePolicy.setRedeliveryDelay(5000);
        cf.setRedeliveryPolicy(queuePolicy);
//Kaha db
        ActiveMQPrefetchPolicy policy = new ActiveMQPrefetchPolicy();
        policy.setQueuePrefetch(0);
        cf.setPrefetchPolicy(policy);
        factory.setConcurrency("2-5");
        factory.setErrorHandler(t -> {
            final Logger LOG = LoggerFactory.getLogger(DefaultJmsListenerContainerFactory.class);
            LOG.warn("In default jms error handler...");
            LOG.error("Error Message : {}", t.getMessage());
        });
        return factory;
    }

    @Bean
    public JmsMessagingTemplate jmsTemplate() {
        JmsMessagingTemplate template = new JmsMessagingTemplate();
        template.setConnectionFactory(cf);
        return template;
    }
}