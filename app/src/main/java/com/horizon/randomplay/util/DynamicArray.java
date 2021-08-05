package com.horizon.randomplay.util;

import com.horizon.randomplay.series.SeriesHolder;

import java.util.ArrayList;

public class DynamicArray<T> {
    private ArrayList<T> arr;
    private final int SIZE;
    private int size;

    public DynamicArray(int size) {
        this.SIZE = size;
        this.size = size;
        this.arr = new ArrayList<>();
    }

    public void insert(T e) {
        if (arr.size() >= size) {
            arr.remove(0);
        }
        arr.add(e);
    }

    public boolean isExist(T e) {
        for (T t: arr) {
            if (t.equals(e)) {
                return true;
            }
        }
        return false;
    }

    public void changeSize(int size) {
        ArrayList<T> arr = new ArrayList<>();
        size -= 2;
        if (size < this.arr.size()) {
            for (int i = 0; i < size; i++) {
                arr.add(this.arr.get(i));
            }
            this.arr = arr;
            this.size = size;
        } else if (size > this.arr.size()) {
            this.size = size;
        }
    }

    public static int getIdealSize(int size) {
        return (size- 1) / 3;
    }

    public void retrieveDefSize() {
        changeSize(SIZE);
    }
}
