package com.mycompany.app.controller;

import java.util.List;

public class GenericCollection<T> {
    private List<T> items;

    public GenericCollection(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }
    
}
