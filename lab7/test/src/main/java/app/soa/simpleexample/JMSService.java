package app.soa.simpleexample;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.jms.*;

@Named("JMSService")
@ApplicationScoped
public class JMSService {
    @Resource(mappedName = "java:/jms/queue/SOA_test")
    private Queue queueExample;

    @Resource(mappedName = "java:/JmsXA")
    private ConnectionFactory cf;

    private Connection connection;

    public void sendMessage() {
        try {
            String txt = "Test message";
            connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = null;
            publisher = session.createProducer(queueExample);
            connection.start();
            TextMessage message = session.createTextMessage(txt);
            publisher.send(message);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}