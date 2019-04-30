package com.example.BookManagement.converters.bases;

import com.example.BookManagement.exceptions.BadRequestException;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Converter<S,T> {

    public abstract T convert(S source) throws BadRequestException;

    public List<T> convert(List<S> source) throws  BadRequestException {
        return source.stream().map(this::convert).collect(Collectors.toList());
    }
}
