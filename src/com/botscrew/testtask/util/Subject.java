package com.botscrew.testtask.util;

public interface Subject {
	void registerObserver(Observer o);
	void notifyObserver();
}