package app.soa.simpleexample;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
public class QueueSender {
    @Resource(mappedName = "java:/jms/queue/SOA_test")
    private Queue queueTest;

    @Inject
    JMSContext context;
    public void sendMessage(String txt) {
        try {
            context.createProducer().send(queueTest, txt);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}