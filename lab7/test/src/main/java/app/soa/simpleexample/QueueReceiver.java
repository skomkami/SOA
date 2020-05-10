package app.soa.simpleexample;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

public class QueueReceiver {
    @Inject
    private JMSContext context;

    @Resource(mappedName = "java:/jms/queue/SOA_test")
    Queue myQueue;

    public String receiveMessage() {
        String message = context.createConsumer(myQueue).receiveBody(String.class);
        if (message == null)
            message = "Nic nie ma w kolejce";
        return message;
    }
}
