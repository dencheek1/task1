package by.vsu.lab.task4.service;

import by.vsu.lab.task4.service.exception.TransactionException;

public interface Transaction {
    void start() throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;
}
