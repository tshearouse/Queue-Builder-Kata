package com.deere.isg.enhance.kata;

import java.util.Optional;

public interface Queue<T> {
    void clear();
    void add(QueueMessage<T> item);
    Optional<T> poll();
    boolean isEmpty();
    Integer getCapacity();
}
