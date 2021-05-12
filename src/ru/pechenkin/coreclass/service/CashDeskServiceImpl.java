package ru.pechenkin.coreclass.service;

import ru.pechenkin.coreclass.api.CashDesk;
import ru.pechenkin.coreclass.api.CashDeskService;
import ru.pechenkin.coreclass.model.CashDeskImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CashDeskServiceImpl implements CashDeskService {

    List<CashDesk> cashDeskList = new ArrayList<>();

    public CashDeskServiceImpl(int limit, int... throughput) {

        for (int i = 0; i < throughput.length; i++) {
            CashDeskImpl cashDesk = new CashDeskImpl(String.valueOf(i + 1), limit, throughput[i]);
            cashDeskList.add(cashDesk);
        }

    }

    @Override
    public CashDesk getCashDesk(int index) {

        if (index < 0 || index > cashDeskList.size() - 1) {
            throw new RuntimeException("Нет кассы с индексом " + index);
        }

        return cashDeskList.get(index);
    }

    @Override
    public Optional<CashDesk> getFreeCashDesk() {

        CashDesk result = null;

        for (CashDesk cashDesk : cashDeskList) {

            if (!cashDesk.hasSpace()) {
                continue;
            }

            if (result == null || cashDesk.waitingTime() < result.waitingTime()) {
                result = cashDesk;
            }

        }

        return Optional.ofNullable(result);

    }


}
