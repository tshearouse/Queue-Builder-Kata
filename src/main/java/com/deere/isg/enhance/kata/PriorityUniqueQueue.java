package com.deere.isg.enhance.kata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

public class PriorityUniqueQueue<T> implements Queue<T> {

    private final Integer capacity;
    private final HashMap<Integer, HashSet<T>> items;

    public PriorityUniqueQueue(Integer capacity) {
        this.capacity = capacity;
        items = new HashMap<>();
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public void add(QueueMessage<T> item) {
        if (getCurrentQueueDepth() == capacity)
            throw new RuntimeException("Queue is at capacity");
        var itemsForPriority = items.getOrDefault(item.priority(), new HashSet<>());
        itemsForPriority.add(item.payload());
        items.put(item.priority(), itemsForPriority);
    }

    @Override
    public Optional<T> poll() {
        var topPriorityValue = items.keySet().stream().sorted().findFirst();
        return topPriorityValue.map(priority -> {
            var itemsWithTopPriority = items.get(priority);
            var item = itemsWithTopPriority.stream().findFirst().get();
            itemsWithTopPriority.remove(item);
            if (itemsWithTopPriority.isEmpty())
                items.remove(priority);
            return item;
        });
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public Integer getCapacity() {
        return capacity;
    }

    private int getCurrentQueueDepth() {
        return items.values().stream().mapToInt(HashSet::size).sum();
    }
}
