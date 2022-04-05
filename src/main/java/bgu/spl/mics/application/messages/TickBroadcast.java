package bgu.spl.mics.application.messages;

import bgu.spl.mics.Broadcast;

import java.util.concurrent.atomic.AtomicInteger;

public class TickBroadcast implements Broadcast {
    private AtomicInteger currentTick=new AtomicInteger(1);

    public int getCurrentTick() {
        return currentTick.get();
    }

    public boolean compareAndSetCurrentTick(int cur,int next) {
        return this.currentTick.compareAndSet(cur,next);
    }
}
