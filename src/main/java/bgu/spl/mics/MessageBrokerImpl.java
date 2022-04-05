package bgu.spl.mics;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * The {@link MessageBrokerImpl class is the implementation of the MessageBroker interface.
 * Write your implementation here!
 * Only private fields and methods can be added to this class.
 */
public class MessageBrokerImpl implements MessageBroker {
	private ConcurrentHashMap<Class<? extends Event>, ConcurrentLinkedQueue<Subscriber>> roundRobin;
	private ConcurrentHashMap<Subscriber, LinkedList<Class<? extends Message>>> messageType;
	private ConcurrentHashMap<Subscriber, BlockingQueue<Message>> map;
	private ConcurrentHashMap<Event, Future> futureMap;
	private static class SingletonHolder{
		private static MessageBroker instance = new MessageBrokerImpl();
	}
	/**
	 * Retrieves the single instance of this class.
	 */
	public static MessageBroker getInstance() {
			return SingletonHolder.instance;
	}

	private MessageBrokerImpl()
	{
		synchronized (this) {
			roundRobin = new ConcurrentHashMap<>();
			messageType = new ConcurrentHashMap<>();
			map = new ConcurrentHashMap<>();
			futureMap = new ConcurrentHashMap<>();
		}
	}

	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, Subscriber m) {
		roundRobin.putIfAbsent(type,new ConcurrentLinkedQueue<>());
		synchronized (roundRobin.get(type)){
			ConcurrentLinkedQueue<Subscriber> current = roundRobin.get(type);
			if(!current.contains(m))
				current.add(m);
		}
		messageType.putIfAbsent(m, new LinkedList<>());
		synchronized (messageType.get(m)){
			LinkedList<Class<? extends Message>> tmp = messageType.get(m);
			if(!tmp.contains(type))
				tmp.add(type);
		}
	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, Subscriber m) {
		messageType.putIfAbsent(m, new LinkedList<>());
		synchronized (messageType.get(m)) {
			LinkedList<Class<? extends Message>> tmp = messageType.get(m);
			if (!tmp.contains(type))
				tmp.add(type);
		}
	}

	@Override
	public <T> void complete(Event<T> e, T result) {
		Future tmp = futureMap.get(e);
		tmp.resolve(result);
	}

	@Override
	public void sendBroadcast(Broadcast b) {
		for(Map.Entry<Subscriber, LinkedList<Class<? extends Message>>> element : messageType.entrySet()) {
			synchronized (element.getKey()) {
			for (int i = 0; i < element.getValue().size(); i++) {
				if (element.getValue().get(i).equals(b.getClass())) {
						BlockingQueue<Message> tmp = map.get(element.getKey());
						if (tmp != null) {
							tmp.add(b);
							element.getKey().notify();
						}
					}
				}
			}
		}
	}

	
	@Override
	public <T> Future<T> sendEvent(Event<T> e) {
		Future<T> future = new Future<>();
		Subscriber Sub;
		synchronized (e.getClass()) {
			ConcurrentLinkedQueue<Subscriber> temp = roundRobin.get(e.getClass());
			if (temp == null) {
				return null;
			}
			if (temp.isEmpty()) {
				return null;
			}
			Sub = temp.poll();
			temp.add(Sub);
			e.getClass().notify();
		}
		assert Sub != null;
		synchronized (Sub) {
			BlockingQueue<Message> current = map.get(Sub);
			if (current == null) {
				future.resolve(null);
				return null;
			}
			current.add(e);
			Sub.notify();
		}
			futureMap.put(e, future);
		return future;
	}

	@Override
	public void register(Subscriber m) {
		BlockingQueue<Message> d = new LinkedBlockingQueue<>();
		map.put(m,d);
		LinkedList<Class<? extends Message>> l = new LinkedList<>();
		messageType.put(m,l);
	}

	@Override
	public void unregister(Subscriber m) {
		BlockingQueue<Message> current;
		synchronized (m){
			current = map.remove(m);
		}
		while (!current.isEmpty()){
			if(current.peek() instanceof Event){
				Event<?> event = (Event) current.poll();
				Future<?> erase = futureMap.get(event);
				if(!erase.isDone())
					erase.resolve(null);
			}
			else
				current.poll();
		}
		messageType.remove(m);
		for(Map.Entry<Class<? extends Event>, ConcurrentLinkedQueue<Subscriber>> element : roundRobin.entrySet())
			synchronized (element.getValue()){
			element.getValue().remove(m);
			}
	}

	@Override
	public Message awaitMessage(Subscriber m) throws InterruptedException {
		synchronized (m) {
			while (map.get(m).isEmpty()) {
				try {
					m.wait();
				} catch (InterruptedException exception) {
					throw new IllegalStateException(exception.getMessage());
				}
			}
		}
			return map.get(m).poll();
	}
}
