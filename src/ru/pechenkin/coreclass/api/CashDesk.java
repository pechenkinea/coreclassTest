package ru.pechenkin.coreclass.api;

public interface CashDesk {
    int getThroughput();

    String getName();

    void toQueue();

    boolean hasSpace();

    void leaveQueue();

    int waitingTime();
}
