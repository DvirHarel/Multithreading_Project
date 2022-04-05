package bgu.spl.mics.application.passiveObjects;
import javax.print.DocFlavor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Passive data-object representing a information about an agent in MI6.
 * You must not alter any of the given public methods of this class. 
 * <p>
 * You may add ONLY private fields and methods to this class.
 */
public class Squad {
	private static Squad squad = null;
	private ConcurrentHashMap<String, Agent> agents;

	/**
	 * Retrieves the single instance of this class.
	 */
	public static Squad getInstance() {
		if (squad == null)
			squad = new Squad();
		return squad;
	}

	private Squad() {
		agents = new ConcurrentHashMap<>();
	}

	/*public Map<String, Agent> getAgents()
	{
		return this.agents;
	}
*/
	/**
	 * Initializes the squad. This method adds all the agents to the squad.
	 * <p>
	 * @param agents 	Data structure containing all data necessary for initialization
	 * 						of the squad.
	 */
	public void load (Agent[] agents) {
		for (int i = 0; i<agents.length; i++)
		{
			this.agents.put(agents[i].getSerialNumber(),agents[i]);
		}
	}

	/**
	 * Releases agents.
	 */
	public void releaseAgents(List<String> serials){
		for (int i = 0; i<serials.size(); i++)
		{
			if (agents.contains(serials.get(i))) {
				synchronized (agents.get(serials.get(i))) {
					agents.get(serials.get(i)).release();
					agents.get(serials.get(i)).notify();
				}
			}
		}
	}

	/**
	 * simulates executing a mission by calling sleep.
	 * @param time   milliseconds to sleep
	 */
	public void sendAgents(List<String> serials, int time) {
		try {
			Thread.sleep(time*100);
			releaseAgents(serials);
		}
		catch (InterruptedException exception) {}
	}

	/**
	 * acquires an agent, i.e. holds the agent until the caller is done with it
	 * @param serials   the serial numbers of the agents
	 * @return ‘false’ if an agent of serialNumber ‘serial’ is missing, and ‘true’ otherwise
	 */
	public boolean getAgents(List<String> serials) throws InterruptedException {
		boolean ans = true;
		for (int i = 0; i<serials.size(); i++)
		{
			boolean help = false;
			if (agents.contains(serials.get(i))) {
				synchronized (agents.get(serials.get(i))) {
					while (!agents.get(serials.get(i)).isAvailable()) {
						wait();
					}
					agents.get(serials.get(i)).acquire();
					help = true;
				}
			}
			if (!help)
				ans = false;
		}
		return ans;
	}

	/**
	 * gets the agents names
	 * @param serials the serial numbers of the agents
	 * @return a list of the names of the agents with the specified serials.
	 */
	public List<String> getAgentsNames(List<String> serials){
		List<String> names = null;
		for (int i = 0; i<serials.size(); i++)
		{
			if (agents.contains(serials.get(i)))
				names.add(agents.get(serials.get(i)).getName());
		}
		return names;
	}

}