package com.horizon.randomplay.util;

import java.util.ArrayList;

public class DynamicArray<T> {
    private final ArrayList<T> arr;
    private final int SIZE;

    public DynamicArray(int size) {
        this.SIZE = size;
        this.arr = new ArrayList<>();
    }

    public void insert(T e) {
        if (arr.size() >= SIZE) {
            arr.remove(0);
        }
        arr.add(e);
    }

    public boolean isExist(T e) {
        return arr.contains(e);
    }
}
