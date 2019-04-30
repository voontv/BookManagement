package com.example.BookManagement.converters.bases;

import com.example.BookManagement.exceptions.BadRequestException;

import java.util.ArrayList;
import java.util.List;

public abstract class Converter<S,T> {

    public abstract T convert(S source) throws BadRequestException;

    public List<T> convert(List<S> source) throws  BadRequestException {
        ArrayList<T> result = new ArrayList<>();
        for(S i: source) {
            result.add(convert(i));
        }
        return result;
    }
}
