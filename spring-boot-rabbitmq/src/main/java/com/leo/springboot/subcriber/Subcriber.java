package com.leo.springboot.subcriber;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.leo.springboot.resources.EntregaResource;
@Component
public class Subcriber {
	
	@Autowired
	EntregaResource entregaResource;
	
	@RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void recievedMessage(String msg) {

		entregaResource.cadastraEntrega(msg);
        System.out.println("Recieved Message: " + msg);
    }
}
