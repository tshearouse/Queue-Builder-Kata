package com.deere.isg.enhance.kata;

import java.util.*;

public class UniqueQueue<T> implements Queue<T> {
    private final Integer capacity;
    private final Set<T> items;

    public UniqueQueue(Integer capacity) {
        this.capacity = capacity;
        this.items = new HashSet<T>(capacity);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public void add(QueueMessage<T> item) {
        if (items.size() == capacity)
            throw new RuntimeException("Queue is at capacity");
        items.add(item.payload());
    }

    @Override
    public Optional<T> poll() {
        return items.stream().findAny()
                .map(item -> {
                    items.remove(item);
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
}
