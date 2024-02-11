package com.deere.isg.enhance.kata;

public record QueueMessage<T>(T payload, int priority) {
    public QueueMessage(T payload) {
        this(payload, 0);
    }
}
