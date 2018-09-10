package presenters;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JFileChooser;

import dict.Dictionary;
import dict.DictionaryElement;
import models.MainModel;
import models.StatsModel;
import properties.Config;
import views.MainView;
import views.StatsView;
import views.View;

public class MainPresenter implements Presenter {

    private MainModel model;
    private MainView view;
    private Dictionary dict;

    private static View childFrame;
    private static Presenter childFramePresenter;

    @SuppressWarnings("unused")
    private MainPresenter() {
    }

    public MainPresenter(MainModel model, MainView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void displayView() {
        view.displaySelf();
    }

    public void openDictionary() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(Config.statsFolder));
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                dict = new Dictionary(fileChooser.getSelectedFile());
                deleteNonexistentWordsStats();
                view.setInputEnabled(true);
                loadNewQuestion();
            }
        } catch (Exception e) {
            view.setInputEnabled(false);
            e.printStackTrace();
        }
    }

    private void deleteNonexistentWordsStats() {
        Object[][] stats = StatsModel.getStats();
        for (Object[] wordStats : stats) {
            String word = (String) wordStats[0];
            if (!dict.containsWord(word)) {
                StatsModel.deleteStatsForWord(word);
            }
        }
    }

    public void loadNewQuestion() {
        boolean isTranslateFromGerman = view.getIsTranslateFromGerman();

        DictionaryElement newDictElt = null;
        String newWordToTranslate = null;
        String newGerArticle = null;
        Set<String> newPossibleTranslationsSet = null;
        String newCorrectTranslation = null;
        do {
            newDictElt = dict.getRandomDictionaryElement();
            if (isTranslateFromGerman) {
                newWordToTranslate = newDictElt.getGerWord();
                newCorrectTranslation = newDictElt.getNonGerWord();
            } else {
                newWordToTranslate = newDictElt.getNonGerWord();
                newCorrectTranslation = newDictElt.getGerWord();
            }
        } while (newWordToTranslate.equals(model.getWordToTranslate()));
        newGerArticle = newDictElt.getGerArticle();
        newPossibleTranslationsSet = dict.getNPossibleTranslationsForElement(newDictElt, 5, isTranslateFromGerman);

        model.setTranslateFromGerman(isTranslateFromGerman);
        model.setWordToTranslate(newWordToTranslate);
        model.setGerArticle(newGerArticle);
        model.setPossibleTranslations(newPossibleTranslationsSet);
        model.setCorrectTranslation(newCorrectTranslation);

        List<String> newPossibleTranslationsList = new ArrayList<>(newPossibleTranslationsSet);
        Collections.shuffle(newPossibleTranslationsList, new Random(System.nanoTime()));
        view.displayNewQuestion(newWordToTranslate, newGerArticle, newPossibleTranslationsList);
    }

    public void checkArticle() {
        view.displayCorrectArticle(model.getGerArticle());
    }

    public void checkTranslation() {
        view.displayCorrectTranslation(model.getCorrectTranslation());
    }

    private void recordNewAnswer(String key, boolean isCorrect, boolean fromGerman) {
        StatsModel.recordNewAnswer(key, isCorrect);
        view.stopFiringStatsUpdates();
    }

    public void updateStats() {
        String key = view.getIsTranslateFromGerman() ? model.getWordToTranslate() : model.getCorrectTranslation();
        key += (model.getGerArticle() == null ? "" : " (" + model.getGerArticle() + ")");
        boolean isArticleSelected = view.getSelectedArticle() != null;
        boolean isTranslationSelected = view.getSelectedTranslation() != null;
        if (isArticleSelected) {
            if (!view.getSelectedArticle().equals(model.getGerArticle())) {
                recordNewAnswer(key, false, true);
                return;
            }
            if (isTranslationSelected) {
                recordNewAnswer(key, view.getSelectedTranslation().equals(model.getCorrectTranslation()), true);
            }
        } else {
            if (!view.getSelectedTranslation().equals(model.getCorrectTranslation())) {
                recordNewAnswer(key, false, true);
            } else if (model.getGerArticle() == null) {
                recordNewAnswer(key, true, true);
            }
        }
    }

    public void displayStats() {
        childFrame = new StatsView();
        childFramePresenter = new StatsPresenter(childFrame);
        childFrame.setPresenter(childFramePresenter);
        childFramePresenter.displayView();
    }
}
