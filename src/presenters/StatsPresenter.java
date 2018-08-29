package presenters;

import java.util.Arrays;
import java.util.Comparator;

import models.StatsModel;
import views.StatsView;
import views.View;

public class StatsPresenter implements Presenter {

	private StatsView view;

	@SuppressWarnings("unused")
	private StatsPresenter() {
	}

	public StatsPresenter(View view) {
		this.view = (StatsView) view;
	}

	@Override
	public void displayView() {
		Object[][] stats = StatsModel.getStats();
		Arrays.sort(stats, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] o1, Object[] o2) {
				return ((String) o1[0]).toLowerCase().compareTo(((String) o2[0]).toLowerCase());
			}
		});
		view.setStats(stats);
		view.displaySelf();
	}

	public void deleteSelectedWordsStats() {
		for (String word : view.getSelectedWords()) {
			StatsModel.deleteStatsForWord(word);
		}
		displayView();
	}

	public void clearRecentStats() {
		StatsModel.clearRecentStats();
		displayView();
	}

	public void deleteStats() {
		StatsModel.deleteStats();
		displayView();
	}
}
