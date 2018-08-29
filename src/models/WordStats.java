package models;

import java.util.LinkedList;
import java.util.Queue;

import properties.Config;

class WordStats implements java.io.Serializable {

	private static final long serialVersionUID = 3035661451118771533L;

	private Queue<Boolean> recentAnswers;
	private int recentAnswersCount;
	private int recentCorrectAnswersCount;
	private int overallAnswersCount;
	private int overallCorrectAnswersCount;

	WordStats() {
		this.recentAnswers = new LinkedList<>();
		this.recentAnswersCount = 0;
		this.recentCorrectAnswersCount = 0;
		this.overallAnswersCount = 0;
		this.overallCorrectAnswersCount = 0;
	}

	int getRecentAnswersCount() {
		return recentAnswersCount;
	}

	int getOverallAnswersCount() {
		return overallAnswersCount;
	}

	double getRecentAccuracy() {
		return recentAnswersCount == 0 ? 0.0 : (double) recentCorrectAnswersCount / recentAnswersCount;
	}

	double getOverallAccuracy() {
		return overallAnswersCount == 0 ? 0.0 : (double) overallCorrectAnswersCount / overallAnswersCount;
	}

	void clearRecentStats() {
		recentAnswersCount = 0;
		recentCorrectAnswersCount = 0;
	}

	void recordNewAnswer(boolean isCorrectAnswer) {
		recentAnswers.add(isCorrectAnswer);
		overallAnswersCount += 1;
		recentAnswersCount += 1;
		if (isCorrectAnswer) {
			overallCorrectAnswersCount += 1;
			recentCorrectAnswersCount += 1;
		}
		while (recentAnswersCount > Config.recentAnswersLimit) {
			boolean oldestRecentAnswerWasCorrect = recentAnswers.remove();
			if (oldestRecentAnswerWasCorrect) {
				recentCorrectAnswersCount -= 1;
			}
			recentAnswersCount -= 1;
		}
	}
}
