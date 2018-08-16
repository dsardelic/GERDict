package mvp;

import java.io.File;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;

import dict.Dictionary;
import dict.DictionaryElement;

public class Presenter {
	private Model model;
	private View view;
	private Dictionary dict;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	private void updateModelWithNewDictionaryElement() {
		DictionaryElement dictElt;
		String untranslatedWord = null;
		String correctTranslation = null;
		do {
			dictElt = dict.getRandomDictionaryElement();
			if (model.getTranslationDirection().equals(Model.FROM_GERMAN)) {
				untranslatedWord = dictElt.getGerWord();
				correctTranslation = dictElt.getNonGerWord();
			} else if (model.getTranslationDirection().equals(Model.TO_GERMAN)) {
				untranslatedWord = dictElt.getNonGerWord();
				correctTranslation = dictElt.getGerWord();
			}
		} while (untranslatedWord.equals(model.getUntranslatedWord()));
		model.setUntranslatedWord(untranslatedWord);
		model.setCorrectGerArticle(dictElt.getGerArticle());
		model.setPossibleTranslations(
				dict.getNPossibleTranslationsForElement(5, dictElt, model.getTranslationDirection()));
		model.setCorrectTranslation(correctTranslation);
	}

	public void loadNewDictionaryElement() {
		updateModelWithNewDictionaryElement();
		view.loadDictionaryElement();
	}

	public void run() {
		view.setPresenter(this);
		view.setVisible(true);
	}

	public void loadDictionary() {
		JFileChooser fileChooser = new JFileChooser();
		try {
			fileChooser.setCurrentDirectory(new File(Presenter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getParentFile());
		} catch (URISyntaxException e1) {
			fileChooser.setCurrentDirectory(new File(".."));
		}
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				dict = new Dictionary(fileChooser.getSelectedFile());
				loadNewDictionaryElement();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}