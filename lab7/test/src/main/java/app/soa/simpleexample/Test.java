package app.soa.simpleexample;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.*;

@Named("Test")
@RequestScoped
public class Test  {
    @Resource(mappedName="java:/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(mappedName="java:/jms/queue/SOA_test")
    private static Queue queue;

    public void sendMessage() throws JMSException {
        Connection conn = cf.createConnection();
        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Queue queue = session.createQueue("java:/SOA_TestQueue");
        MessageProducer producer = session.createProducer(queue);
        TextMessage msg = session.createTextMessage();
        msg.setText("Komunikat testowy");
        producer.send(msg);
    }

}
