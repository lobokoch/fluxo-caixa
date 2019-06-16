/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa;

import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import br.com.kerubin.api.messaging.core.DomainEvent;

@Configuration
public class ServerConfig {
	
	private static final String dateFormat = "yyyy-MM-dd";
    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
 
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(dateTimeFormat);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
            
            ObjectMapper objectMapper = new ObjectMapper();
    		objectMapper.registerModule(new JavaTimeModule());
    		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    		
    		builder.configure(objectMapper);
        };
    }
	
	// TODO: remover esse cara
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
	
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer,
	        ConnectionFactory connectionFactory) {
		
	    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	    configurer.configure(factory, connectionFactory);
	    factory.setConsumerTagStrategy(queue -> {
	    	StringBuilder sb = new StringBuilder(DomainEvent.APPLICATION).append("_")
	    			.append(FinanceiroFluxoCaixaConstants.DOMAIN)
	    			.append("_")
	    			.append(FinanceiroFluxoCaixaConstants.SERVICE)
	    			.append("_")
	    			.append(UUID.randomUUID().toString())
	    			//.append(".to.")
	    			//.append(queue)
	    			;
	    	String tag = sb.toString();
	    	return tag;
	    });
	    
	    factory.setAfterReceivePostProcessors(afterReceivePostProcessors());
	    
	    return factory;
	}
	
	@Bean
	public MessagePostProcessor afterReceivePostProcessors() {
		return new MessageAfterReceivePostProcessors();
	}
}
