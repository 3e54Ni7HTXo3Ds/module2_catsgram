package ru.yandex.practicum.catsgram.Exeptions;

public class InvalidEmailException extends Throwable{
    public InvalidEmailException() {
    }

    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
