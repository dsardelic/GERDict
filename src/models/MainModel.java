package models;

import java.util.Set;

public class MainModel {

	private boolean isTranslateFromGerman;
	private String wordToTranslate;
	private String gerArticle;
	private Set<String> possibleTranslations;
	private String correctTranslation;

	public boolean isTranslateFromGerman() {
		return isTranslateFromGerman;
	}

	public void setTranslateFromGerman(boolean isTranslateFromGerman) {
		this.isTranslateFromGerman = isTranslateFromGerman;
	}

	public String getWordToTranslate() {
		return wordToTranslate;
	}

	public void setWordToTranslate(String wordToTranslate) {
		this.wordToTranslate = wordToTranslate;
	}

	public String getGerArticle() {
		return gerArticle;
	}

	public void setGerArticle(String gerArticle) {
		this.gerArticle = gerArticle;
	}

	public Set<String> getPossibleTranslations() {
		return possibleTranslations;
	}

	public void setPossibleTranslations(Set<String> possibleTranslations) {
		this.possibleTranslations = possibleTranslations;
	}

	public String getCorrectTranslation() {
		return correctTranslation;
	}

	public void setCorrectTranslation(String correctTranslation) {
		this.correctTranslation = correctTranslation;
	}
}
