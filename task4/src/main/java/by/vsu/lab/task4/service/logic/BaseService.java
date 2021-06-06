package by.vsu.lab.task4.service.logic;

import by.vsu.lab.task4.service.Transaction;
import by.vsu.lab.task4.service.exception.TransactionException;

public abstract class BaseService {

    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}
