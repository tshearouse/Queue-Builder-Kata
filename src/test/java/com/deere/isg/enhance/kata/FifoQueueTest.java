package com.deere.isg.enhance.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FifoQueueTest {

    private FifoQueue<Integer> queue;

    @BeforeEach
    void setup() {
        queue = new FifoQueue<>(5);
    }

    @Test
    void pollShouldReturnOldestItem() {
        queue.add(new QueueMessage<>(1));
        queue.add(new QueueMessage<>(2));

        assertEquals(1, queue.poll().get());
    }

    @Test
    void pollingShouldRemoveItemFromQueue() {
        queue.add(new QueueMessage<>(1));

        assertFalse(queue.poll().isEmpty());
        assertTrue(queue.poll().isEmpty());
    }

    @Test
    void pollingEmptyQueueShouldReturnEmpty() {
        assertTrue(queue.poll().isEmpty());
    }

    @Test
    void clearShouldResultInEmptyQueue() {
        queue.add(new QueueMessage<>(1));
        queue.clear();

        assertTrue(queue.isEmpty());
        assertTrue(queue.poll().isEmpty());
    }

    @Test
    void addShouldThrowExceptionWhenQueueIsFull() {
        queue = new FifoQueue<>(0);
        assertThrows(RuntimeException.class, () -> queue.add(new QueueMessage<>(1)));
    }

    @Test
    void capacityShouldMatchConstructor() {
        queue = new FifoQueue<>(42);
        assertEquals(42, queue.getCapacity());
    }
}