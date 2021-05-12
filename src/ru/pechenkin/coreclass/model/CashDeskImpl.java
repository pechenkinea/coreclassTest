package ru.pechenkin.coreclass.model;

import ru.pechenkin.coreclass.api.CashDesk;

public class CashDeskImpl implements CashDesk {

    private final int throughput;
    // сколько надо время на 1 человека в секундах
    private final int throughputSec;

    private int queueLength = 0;
    private final int limit;
    private final String name;

    public CashDeskImpl(String name, int limit, int throughput) {
        this.name = name;
        this.throughput = throughput;
        this.throughputSec = (60 * 60) / throughput;
        this.limit = limit;
    }

    @Override
    public int getThroughput() {
        return throughput;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void toQueue() {

        if (limit == queueLength) {
            throw new RuntimeException("Зовите Галю, в эту кассу уже не помещаемся");
        }

        this.queueLength++;
    }

    @Override
    public boolean hasSpace() {
        return queueLength < limit;
    }

    @Override
    public void leaveQueue() {
        this.queueLength--;

        if (this.queueLength < 0) {
            this.queueLength = 0;
            throw new RuntimeException("Нет очереди в кассе");
        }
    }

    /*
        Прогноз времени ожидания в секундах
     */
    @Override
    public int waitingTime() {
        return this.throughputSec * this.queueLength;
    }

}
