package main;

import java.awt.EventQueue;

import mvp.Model;
import mvp.Presenter;
import mvp.View;
import properties.Config;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Config.readConfig();
					Model model = new Model();
					View view = new View();
					Presenter presenter = new Presenter();
					presenter.setModel(model);
					presenter.setView(view);
					presenter.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}