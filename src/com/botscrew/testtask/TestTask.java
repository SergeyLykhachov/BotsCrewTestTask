package com.botscrew.testtask;

import com.botscrew.testtask.model.Model;
import com.botscrew.testtask.view.View;

public class TestTask {
	public TestTask() {
		new View(new Model());
	}
	public static void main(String[] sa) {
		new TestTask();
	}
}
