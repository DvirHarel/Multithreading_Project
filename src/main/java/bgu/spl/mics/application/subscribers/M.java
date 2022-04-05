package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.Future;
import bgu.spl.mics.Subscriber;
import bgu.spl.mics.application.messages.AgentsAvailableEvent;
import bgu.spl.mics.application.messages.GadgetAvailableEvent;
import bgu.spl.mics.application.messages.MissionReceivedEvent;
import bgu.spl.mics.application.messages.TickBroadcast;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.MissionInfo;
import bgu.spl.mics.application.passiveObjects.Report;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * M handles ReadyEvent - fills a report and sends agents to mission.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class M extends Subscriber {
	private static int id = 0;
	private Diary diary = Diary.getInstance();
	AtomicInteger tick = new AtomicInteger(1);
	List<Future<Object>> futures;

	public M() {
		super(Integer.toString(id));
		id++;
	}

	@Override
	protected void initialize() {
		subscribeBroadcast(TickBroadcast.class, bc -> tick.set(bc.getCurrentTick()));

		subscribeEvent(MissionReceivedEvent.class, (ev) -> {
			diary.incrementTotal();
			MissionInfo mission = ev.getMission();
			System.out.println("M " + id + " take the event " + ev.getMission().getMissionName());
			Future<Boolean> people = getSimplePublisher().sendEvent(new AgentsAvailableEvent(mission.getSerialAgentsNumbers()));
			if(people != null)
			{
				Future<Boolean> future1 = getSimplePublisher().sendEvent(new GadgetAvailableEvent(mission.getGadget()));
				if(future1 != null){
					Report newReport = new Report();
					newReport.setAgentsSerialNumbersNumber(mission.getSerialAgentsNumbers());
					newReport.setGadgetName(mission.getGadget());
					newReport.setM(id);
					newReport.setMissionName(mission.getMissionName());
					newReport.setTimeIssued(mission.getTimeIssued());
					diary.addReport(newReport);
				}
			}
		}
		);
	}

}
