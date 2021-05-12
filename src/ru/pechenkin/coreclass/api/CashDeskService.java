package ru.pechenkin.coreclass.api;

import java.util.Optional;

public interface CashDeskService {
    CashDesk getCashDesk(int index);

    Optional<CashDesk> getFreeCashDesk();
}
