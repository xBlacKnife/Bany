package com.example.awsdemo.api.model;

// Generic interface for the Observable pattern.
public interface Observable<T> {

    // Adds an observer.
    void addObserver(T o);

    // Removes an observer.
    void removeObserver(T o);
}
