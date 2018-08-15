package mvp;

import java.util.Set;

import properties.Messages;

public class Model {
	public static final String DER = "der";
	public static final String DIE = "die";
	public static final String DAS = "das";
	public static final String FROM_GERMAN = Messages.getMessage(Model.class, "FROM_GERMAN");
	public static final String TO_GERMAN = Messages.getMessage(Model.class, "TO_GERMAN");

	private String translationDirection;
	private String untranslatedWord;
	private String selectedGerArticle;
	private String correctGerArticle;
	private Set<String> possibleTranslations;
	private String selectedTranslation;
	private String correctTranslation;

	public String getTranslationDirection() {
		return translationDirection;
	}

	public void setTranslationDirection(String translationDirection) {
		this.translationDirection = translationDirection;
	}

	public String getUntranslatedWord() {
		return untranslatedWord;
	}

	public void setUntranslatedWord(String untranslatedWord) {
		this.untranslatedWord = untranslatedWord;
	}

	public String getSelectedGerArticle() {
		return selectedGerArticle;
	}

	public void setSelectedGerArticle(String selectedGerArticle) {
		this.selectedGerArticle = selectedGerArticle;
	}

	public String getCorrectGerArticle() {
		return correctGerArticle;
	}

	public void setCorrectGerArticle(String correctGerArticle) {
		this.correctGerArticle = correctGerArticle;
	}

	public Set<String> getPossibleTranslations() {
		return possibleTranslations;
	}

	public void setPossibleTranslations(Set<String> possibleTranslations) {
		this.possibleTranslations = possibleTranslations;
	}

	public String getSelectedTranslation() {
		return selectedTranslation;
	}

	public void setSelectedTranslation(String selectedTranslation) {
		this.selectedTranslation = selectedTranslation;
	}

	public String getCorrectTranslation() {
		return correctTranslation;
	}

	public void setCorrectTranslation(String correctTranslation) {
		this.correctTranslation = correctTranslation;
	}

}