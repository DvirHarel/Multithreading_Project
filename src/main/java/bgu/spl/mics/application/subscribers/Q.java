package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.Subscriber;
import bgu.spl.mics.application.messages.AgentsAvailableEvent;
import bgu.spl.mics.application.messages.GadgetAvailableEvent;
import bgu.spl.mics.application.passiveObjects.Inventory;

/**
 * Q is the only Subscriber\Publisher that has access to the {@link bgu.spl.mics.application.passiveObjects.Inventory}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class Q extends Subscriber {
	private Inventory inventory = Inventory.getInstance();

	public Q() {
		super("q");
	}

	@Override
	protected void initialize() {
		System.out.println("Event Handler " + getName() + " started");
		subscribeEvent(GadgetAvailableEvent.class, ge -> {
			System.out.println("Event Handler " + getName() + " got a new event)");
			complete(ge, inventory.getItem(ge.getGadget()));
			System.out.println("Event Handler " + getName() + " terminating.");
			terminate();
		});

	}

}
