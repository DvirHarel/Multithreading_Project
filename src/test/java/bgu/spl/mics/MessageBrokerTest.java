package bgu.spl.mics;

import bgu.spl.mics.application.messages.TickBroadcast;
import bgu.spl.mics.application.subscribers.M;
import bgu.spl.mics.application.subscribers.Moneypenny;
import bgu.spl.mics.application.subscribers.Q;
//import jdk.internal.util.xml.impl.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MessageBrokerTest {
    private MessageBroker broker;
    @BeforeEach
    public void setUp(){
        this.broker = MessageBrokerImpl.getInstance();
    }

    @Test
    public void testRegister1(){
        Subscriber m = new M();
        Subscriber m1 = new M();
        Subscriber m2 = new M();
        //Subscriber m3 = new Moneypenny();
       // Subscriber q = new Q();
        broker.register(m);
        broker.register(m1);
        broker.register(m2);
       // broker.register(m3);
       // broker.register(q);
        boolean ans = true;
       // for(Queue<Message> queue : broker.getMap().values())
        {
           // if (!queue.isEmpty())
                ans = false;
        }
        assertEquals(ans,true);
        fail("Not a good test");
    }

    @Test
    public void testUnRegister1(){
        Subscriber m = new M();
        Subscriber m1 = new M();
        Subscriber m2 = new M();
        //Subscriber m3 = new Moneypenny();
       // Subscriber q = new Q();
        broker.register(m);
        broker.register(m1);
        broker.register(m2);
      //  broker.register(m3);
       // broker.register(q);
        broker.unregister(m);
        //boolean ans = broker.getMap().containsKey(m);
        //assertEquals(ans,false);
        fail("Not a good test");
    }

    @Test
    public void testSendBroadcast(){
        Subscriber m = new M();
        Subscriber m1 = new M();
        Subscriber m2 = new M();
       // Subscriber m3 = new Moneypenny();
       // Subscriber q = new Q();
        broker.register(m);
        broker.register(m1);
        broker.register(m2);
      //  broker.register(m3);
       // broker.register(q);
        Broadcast b = new TickBroadcast();
        broker.sendBroadcast(b);
        fail("Not a good test");
    }
}
