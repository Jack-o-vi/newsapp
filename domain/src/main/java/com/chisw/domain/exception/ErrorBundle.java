package com.chisw.domain.exception;

public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}
