package com.deere.isg.enhance.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FifoQueue<T> implements Queue<T> {
    private final Integer capacity;
    private final List<T> items;

    public FifoQueue(Integer capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<T>(capacity);
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
        if (isEmpty())
            return Optional.empty();
        var item = items.get(0);
        items.remove(0);
        return Optional.ofNullable(item);
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
