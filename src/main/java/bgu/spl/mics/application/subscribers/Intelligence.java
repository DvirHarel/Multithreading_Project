package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.Publisher;
import bgu.spl.mics.Subscriber;
import bgu.spl.mics.application.passiveObjects.MissionInfo;

import java.util.List;

/**
 * A Publisher only.
 * Holds a list of Info objects and sends them
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class Intelligence extends Subscriber {

	public Intelligence() {
		super("intelligence");
	}

	@Override
	protected void initialize() {
		// TODO Implement this
	}

}
