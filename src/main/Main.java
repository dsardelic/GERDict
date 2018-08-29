package main;

import java.awt.EventQueue;

import models.MainModel;
import presenters.MainPresenter;
import properties.Config;
import views.MainView;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Config.readConfig();
					MainView view = new MainView();
					MainPresenter presenter = new MainPresenter(new MainModel(), view);
					view.setPresenter(presenter);
					presenter.displayView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
