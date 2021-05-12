package ru.pechenkin.coreclass;

import ru.pechenkin.coreclass.api.CashDesk;
import ru.pechenkin.coreclass.api.CashDeskService;
import ru.pechenkin.coreclass.service.CashDeskServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class App {

    public static void main(String[] args) throws IOException {


        CashDeskService cashDeskService = new CashDeskServiceImpl(20, 10, 13, 15, 17);


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //noinspection InfiniteLoopStatement
        while (true) {

            print("Введите команду:");

            String command = br.readLine();
            if ("A".equals(command)) {
                Optional<CashDesk> freeCashDesk = cashDeskService.getFreeCashDesk();
                if (freeCashDesk.isPresent()) {
                    CashDesk cashDesk = freeCashDesk.get();
                    cashDesk.toQueue();
                    print("Встали в очередь " + cashDesk.getName());
                } else {
                    print("Не получилось встать в очередь. Нет свободной кассы");
                }
                continue;
            }


            try {
                int i = Integer.parseInt(command);
                CashDesk cashDesk = cashDeskService.getCashDesk(i - 1);
                cashDesk.leaveQueue();
                print("Покинули очередь " + cashDesk.getName());
            } catch (NumberFormatException ex) {
                print("Неверные данные");
            } catch (Exception ex) {
                print(ex.getMessage());
            }


        }


    }

    public static void print(String s) {
        System.out.println(s);
    }
}
