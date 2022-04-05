package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.Subscriber;
import bgu.spl.mics.application.messages.AgentsAvailableEvent;
import bgu.spl.mics.application.passiveObjects.Squad;
import bgu.spl.mics.example.messages.ExampleEvent;

/**
 * Only this type of Subscriber can access the squad.
 * Three are several Moneypenny-instances - each of them holds a unique serial number that will later be printed on the report.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class Moneypenny extends Subscriber {
	private static int id = 0;
	private Squad squad = Squad.getInstance();

	public Moneypenny() {
		super(Integer.toString(id));
		id++;
	}

	@Override
	protected void initialize() {
		System.out.println("Event Handler " + getName() + " started");
		subscribeEvent(AgentsAvailableEvent.class, ev -> {
			System.out.println("Event Handler " + getName() + " got a new event)");
			complete(ev, squad.getAgents(ev.getSerials()));
				System.out.println("Event Handler " + getName() + " terminating.");
				terminate();
		});
		
	}

}
