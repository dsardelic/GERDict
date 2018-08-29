package views;

import presenters.Presenter;

public interface View {
	
	public void setPresenter(Presenter presenter);
	
	public void displaySelf();
}
