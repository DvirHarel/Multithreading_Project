package bgu.spl.mics.application;

import java.awt.font.FontRenderContext;
import java.io.FileReader;

import bgu.spl.mics.application.passiveObjects.*;
import bgu.spl.mics.application.publishers.TimeService;
import bgu.spl.mics.application.subscribers.Intelligence;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import bgu.spl.mics.application.subscribers.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** This is the Main class of the application. You should parse the input file,
 * create the different instances of the objects, and run the system.
 * In the end, you should output serialized objects.
 */
public class MI6Runner {
    public static void main(String[] args) {
        try{
            LinkedTreeMap reading = null;
            reading = new Gson().fromJson(new FileReader(args[0]),LinkedTreeMap.class);
            ArrayList<String> inventory = (ArrayList) reading.get("inventory");
            Inventory.getInstance().load(inventory.toArray(new String[inventory.size()]));
            LinkedTreeMap services = (LinkedTreeMap) reading.get("services");
            int Moneypenny = (int) Double.parseDouble(services.get("Moneypenny").toString());
            int M = (int) Double.parseDouble(services.get("M").toString());
            int Time = (int) Double.parseDouble(services.get("time").toString());
            ArrayList<LinkedTreeMap> intelligence = (ArrayList<LinkedTreeMap>) services.get("intelligence");
            ArrayList<Intelligence> count = new ArrayList<>();
            for (int i = 0; i<intelligence.size(); i++)
            {
                ArrayList<LinkedTreeMap> missions = (ArrayList<LinkedTreeMap>) intelligence.get(i).get("missions");
                ArrayList<MissionInfo> mission = new ArrayList<>();
                for (int j = 0; j<missions.size(); j++)
                {
                    mission.add(new MissionInfo(missions.get(j).get("name").toString(),(ArrayList<String>) missions.get(j).get("serialAgentsNumbers"),missions.get(j).get("gadget").toString(),(int)Double.parseDouble(missions.get(j).get("timeIssued").toString()),(int) Double.parseDouble(missions.get(j).get("timeExpired").toString()),(int)Double.parseDouble(missions.get(j).get("duration").toString())));
                }
                count.add(new Intelligence(mission));
            }
            ArrayList<LinkedTreeMap> squad = (ArrayList<LinkedTreeMap>) reading.get("squad");
            ArrayList<Agent> agents = new ArrayList<>();
            for (int k = 0; k<squad.size(); k++)
            {
                agents.add(new Agent(squad.get(k).get("serialNumber").toString(),squad.get(k).get("name").toString()));
            }
            Squad.getInstance().load(agents.toArray(new Agent[squad.size()]));
            Squad current = Squad.getInstance();
            Inventory current1 = Inventory.getInstance();
            System.out.println("");
            ExecutorService executer = Executors.newFixedThreadPool(M + Moneypenny + count.size() + 2);
            List<Runnable> list = new ArrayList<>();
            for (int i = 0; i<M; i++)
                list.add(new M());
            for (int j = 0; j<Moneypenny; j++)
                list.add(new Moneypenny());
            for (int k = 0; k<count.size(); k++)
                list.add(count.get(k));
            list.add(new Q());
            list.add(new TimeService(Time));
            for (int i = 0; i<list.size(); i++) {
                executer.execute(list.get(i));
            }
            Thread.sleep(Time*100);
            executer.shutdown();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
