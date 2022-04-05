package bgu.spl.mics;

import bgu.spl.mics.application.passiveObjects.Agent;
import bgu.spl.mics.application.passiveObjects.Squad;
//import jdk.internal.util.xml.impl.Pair;
//import org.graalvm.compiler.core.aarch64.AArch64ArithmeticLIRGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*public class SquadTest {
    private Squad squad;
    @BeforeEach
    public void setUp(){
      //  squad = new Squad();
    }

    @Test
  /*  public void testLoad1(){
        Agent [] agents = new Agent[0];
        squad.load(agents);
     //   boolean ans = squad.getAgents().containsKey("007");
       // assertEquals(ans,false);
        fail("Not a good test");
    }

    @Test
    public void testLoad2(){
        Agent [] agents = new Agent[1];
        Agent e1 = new Agent();
        e1.setSerialNumber("007");
        e1.setName("James Bond");
        agents[0] = e1;
        squad.load(agents);
        boolean ans = squad.getAgents().containsKey("007");
        assertEquals(ans,true);
        fail("Not a good test");
    }

    @Test
    public void testLoad3(){
        Agent [] agents = new Agent[1];
        Agent e1 = new Agent();
        e1.setSerialNumber("0012");
        e1.setName("Sam Johnston");
        agents[0] = e1;
        squad.load(agents);
      //  boolean ans = squad.getAgents().containsKey("007");
      //  assertEquals(ans,false);
        fail("Not a good test");
    }

    @Test
    public void testLoad4(){
        Agent [] agents = new Agent[3];
        Agent e1 = new Agent();
        e1.setSerialNumber("0012");
        e1.setName("Sam Johnston");
        Agent e2 = new Agent();
        e1.setSerialNumber("007");
        e1.setName("James Bond");
        Agent e3 = new Agent();
        e1.setSerialNumber("002");
        e1.setName("Bill Fairbanks");
        agents[0] = e1;
        agents[1] = e2;
        agents[2] = e3;
        squad.load(agents);
      //  boolean ans = squad.getAgents().containsKey("002");
      //  assertEquals(ans,true);
        fail("Not a good test");
    }

    @Test
    public void testLoad5(){
        Agent [] agents = new Agent[3];
        Agent e1 = new Agent();
        e1.setSerialNumber("0012");
        e1.setName("Sam Johnston");
        Agent e2 = new Agent();
        e1.setSerialNumber("007");
        e1.setName("James Bond");
        Agent e3 = new Agent();
        e1.setSerialNumber("002");
        e1.setName("Bill Fairbanks");
        agents[0] = e1;
        agents[1] = e2;
        agents[2] = e3;
        squad.load(agents);
    //    boolean ans = squad.getAgents().containsKey("0011");
     //   assertEquals(ans,false);
        fail("Not a good test");
    }

    @Test
    public void testReleaseAgents1(){
        Agent [] agents = new Agent[1];
        Agent e1 = new Agent();
        e1.setSerialNumber("007");
        e1.setName("James Bond");
        agents[0] = e1;
        squad.load(agents);
        List<String> list = null;
        list.add("007");
        squad.releaseAgents(list);
        boolean ans = true;
        for (Agent agent : squad.getAgents().values()) {
            if (!agent.isAvailable())
            ans = false;
        }
        assertEquals(ans,true);//suppose to be false
        fail("Not a good test");
    }

    @Test
    public void testReleaseAgents2(){
        Agent [] agents = new Agent[3];
        Agent e1 = new Agent();
        e1.setSerialNumber("0012");
        e1.setName("Sam Johnston");
        Agent e2 = new Agent();
        e1.setSerialNumber("007");
        e1.setName("James Bond");
        Agent e3 = new Agent();
        e1.setSerialNumber("002");
        e1.setName("Bill Fairbanks");
        agents[0] = e1;
        agents[1] = e2;
        agents[2] = e3;
        squad.load(agents);
        List<String> list = null;
        list.add("002");
        list.add("007");
        list.add("0012");
        squad.releaseAgents(list);
        boolean ans = true;
        for (Agent agent : squad.getAgents().values()) {
            if (!agent.isAvailable())
                ans = false;
        }
        assertEquals(ans,true);
        fail("Not a good test");
    }

    @Test
    public void testGetAgentsNames1(){
        List<String> list = null;
        list.add("0012");
        List<String> names = squad.getAgentsNames(list);
        boolean ans = names.isEmpty();
        assertEquals(ans,true);
        fail("Not a good test");
    }

    @Test
    public void testGetAgentsNames2(){
        Agent [] agents = new Agent[1];
        Agent e1 = new Agent();
        e1.setSerialNumber("0012");
        e1.setName("Sam Johnston");
        agents[0] = e1;
        squad.load(agents);
        List<String> list = null;
        list.add("0012");
        List<String> names = squad.getAgentsNames(list);
        boolean ans = names.isEmpty();
        assertEquals(ans,false);
        fail("Not a good test");
    }

    @Test
    public void testGetAgentsNames3(){
        Agent [] agents = new Agent[1];
        Agent e1 = new Agent();
        e1.setSerialNumber("0012");
        e1.setName("Sam Johnston");
        agents[0] = e1;
        squad.load(agents);
        List<String> list = null;
        list.add("0012");
        List<String> names = squad.getAgentsNames(list);
        boolean ans = names.contains("Sam Johnston");
        assertEquals(ans,true);
        fail("Not a good test");
    }

    @Test
    public void testGetAgentsNames4(){
        Agent [] agents = new Agent[3];
        Agent e1 = new Agent();
        e1.setSerialNumber("0012");
        e1.setName("Sam Johnston");
        Agent e2 = new Agent();
        e1.setSerialNumber("007");
        e1.setName("James Bond");
        Agent e3 = new Agent();
        e1.setSerialNumber("002");
        e1.setName("Bill Fairbanks");
        agents[0] = e1;
        agents[1] = e2;
        agents[2] = e3;
        squad.load(agents);
        List<String> list = null;
        list.add("002");
        list.add("007");
        list.add("0012");
        List<String> names = squad.getAgentsNames(list);
        boolean ans = (names.contains("Sam Johnston") & names.contains("Bill Fairbanks") & names.contains("James Bond"));
        assertEquals(ans,true);
        fail("Not a good test");
    }

    @Test
    public void testGetAgents1(){
        List<String> list = null;
        list.add("0012");
        boolean ans = squad.getAgents(list);
        assertEquals(ans,false);
        fail("Not a good test");
    }

    @Test
    public void testGetAgents2(){
        Agent [] agents = new Agent[1];
        Agent e1 = new Agent();
        e1.setSerialNumber("0012");
        e1.setName("Sam Johnston");
        agents[0] = e1;
        squad.load(agents);
        List<String> list = null;
        list.add("0012");
        boolean ans = squad.getAgents(list);
        assertEquals(ans,true);
        fail("Not a good test");
    }

    @Test
    public void testGetAgents3(){
        Agent [] agents = new Agent[3];
        Agent e1 = new Agent();
        e1.setSerialNumber("0012");
        e1.setName("Sam Johnston");
        Agent e2 = new Agent();
        e1.setSerialNumber("007");
        e1.setName("James Bond");
        Agent e3 = new Agent();
        e1.setSerialNumber("002");
        e1.setName("Bill Fairbanks");
        agents[0] = e1;
        agents[1] = e2;
        agents[2] = e3;
        squad.load(agents);
        List<String> list = null;
        list.add("0012");
        list.add("002");
        list.add("007");
        boolean ans = squad.getAgents(list);
        assertEquals(ans,true);
        fail("Not a good test");
    }

    @Test
    public void testGetAgents4(){
        Agent [] agents = new Agent[3];
        Agent e1 = new Agent();
        e1.setSerialNumber("0012");
        e1.setName("Sam Johnston");
        Agent e2 = new Agent();
        e1.setSerialNumber("007");
        e1.setName("James Bond");
        Agent e3 = new Agent();
        e1.setSerialNumber("002");
        e1.setName("Bill Fairbanks");
        agents[0] = e1;
        agents[1] = e2;
        agents[2] = e3;
        squad.load(agents);
        List<String> list = null;
        list.add("0012");
        list.add("002");
        list.add("008");
        boolean ans = squad.getAgents(list);
        assertEquals(ans,false);
        fail("Not a good test");
    }
}
*/