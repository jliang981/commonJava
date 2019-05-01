package com.step.jliang.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

/**
 * @author haoliang
 * @Date 2019-04-14
 **/
public class TestEventBus {

    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));
        eventBus.post(1);

        System.out.println("LastMessage:" + listener.getLastMessage());
    }

}

class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:" + lastMessage);
    }

    @Subscribe
    public void watch(Integer a) {
        System.out.println("watch: " + a);

    }

    public int getLastMessage() {
        return lastMessage;
    }
}

class TestEvent {
    int message;

    public TestEvent(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}
