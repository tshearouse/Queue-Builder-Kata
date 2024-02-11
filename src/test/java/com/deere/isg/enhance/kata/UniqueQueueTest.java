package com.deere.isg.enhance.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueQueueTest {
    private UniqueQueue<Integer> queue;

    @BeforeEach
    void setup() {
        queue = new UniqueQueue<>(5);
    }

    @Test
    void pollShouldReturnOldestItem() {
        queue.add(new QueueMessage<>(1));
        queue.add(new QueueMessage<>(2));

        assertEquals(1, queue.poll().get());
    }

    @Test
    void addingDuplicateItemsShouldResultInOneQueueMessage() {
        queue.add(new QueueMessage<>(1));
        queue.add(new QueueMessage<>(1));

        assertTrue(queue.poll().isPresent());
        assertFalse(queue.poll().isPresent());
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
        queue = new UniqueQueue<>(0);
        assertThrows(RuntimeException.class, () -> queue.add(new QueueMessage<>(1)));
    }

    @Test
    void capacityShouldMatchConstructor() {
        queue = new UniqueQueue<>(42);
        assertEquals(42, queue.getCapacity());
    }

}