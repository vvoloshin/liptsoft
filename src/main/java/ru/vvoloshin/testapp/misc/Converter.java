package ru.vvoloshin.testapp.misc;

import lombok.NonNull;

public interface Converter<F, T> {
    T convert(@NonNull F from);
}
