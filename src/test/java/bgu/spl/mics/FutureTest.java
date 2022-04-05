package bgu.spl.mics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class FutureTest {
    private Future<Boolean> future;
    private Future<Integer> future2;
    private Future<String> future3;

    @BeforeEach
    public void setUp() {
        future = new Future();
        future2 = new Future();
        future3 = new Future();
    }

    @Test
    public void testGet1() {
        Boolean ob1 = false;
        this.future.resolve(ob1);
        assertEquals(future.get(), true);
    }

    @Test
    public void testGet2() {
        Integer ob1 = 10;
        this.future2.resolve(ob1);
        assertEquals(future2.get(), 10);
    }
    @Test
    public void testGet3() {
        String ob1 = "123";
        this.future3.resolve(ob1);
        assertEquals(future3.get(), "123");
    }

    @Test
    public void testGetType2Round1() {
        assertNull(future.get(100, TimeUnit.MILLISECONDS));
    }
    @Test
    public void testGetType2Round2() {
        assertNull(future.get(1, TimeUnit.SECONDS));
    }

    @Test
    public void testResolve() {
        Boolean ob1 = false;
        future.resolve(ob1);
        assertEquals(future.isDone(), true);
    }

    @Test
    public void testIsDone() {
        assertEquals(future.isDone(), false);
    }
}

