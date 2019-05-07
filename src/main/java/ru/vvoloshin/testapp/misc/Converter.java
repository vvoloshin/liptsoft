package ru.vvoloshin.testapp.misc;

public interface Converter<F, T> {
    T convert(F from);
}

