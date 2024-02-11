package com.deere.isg.enhance.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityUniqueQueueTest {
    private PriorityUniqueQueue<Integer> queue;

    @BeforeEach
    void setup() {
        queue = new PriorityUniqueQueue<>(5);
    }

    @Test
    void pollShouldReturnOldestItem() {
        queue.add(new QueueMessage<>(1));
        queue.add(new QueueMessage<>(2));

        assertEquals(1, queue.poll().get());
    }

    @Test
    void pollShouldReturnTopPriorityItem() {
        queue.add(new QueueMessage<>(1, 2));
        queue.add(new QueueMessage<>(2, 1));

        assertEquals(2, queue.poll().get());
    }

    @Test
    void addingDuplicateItemsShouldResultInOneItem() {
        queue.add(new QueueMessage<>(1, 1));
        queue.add(new QueueMessage<>(1, 1));

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
        queue = new PriorityUniqueQueue<>(0);
        assertThrows(RuntimeException.class, () -> queue.add(new QueueMessage<>(1)));
    }

    @Test
    void addShouldCheckCapacityAcrossAllPriorities() {
        queue = new PriorityUniqueQueue<>(2);
        queue.add(new QueueMessage<>(1, 1));
        queue.add(new QueueMessage<>(2, 2));
        assertThrows(RuntimeException.class, () -> queue.add(new QueueMessage<>(3, 3)));
    }

    @Test
    void capacityShouldMatchConstructor() {
        queue = new PriorityUniqueQueue<>(42);
        assertEquals(42, queue.getCapacity());
    }
}