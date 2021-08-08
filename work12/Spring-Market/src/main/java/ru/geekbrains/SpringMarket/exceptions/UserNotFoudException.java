package ru.geekbrains.SpringMarket.exceptions;

public class UserNotFoudException extends RuntimeException {
    public UserNotFoudException(String message) {
        super(message);
    }
}
