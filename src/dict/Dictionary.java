package dict;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mvp.Model;

public class Dictionary {
	private List<DictionaryElement> eltsWithGerArticles;
	private List<DictionaryElement> eltsWithoutGerArticles;

	public Dictionary(File dictFile) throws Exception {
		this.eltsWithGerArticles = new ArrayList<DictionaryElement>();
		this.eltsWithoutGerArticles = new ArrayList<DictionaryElement>();
		readDictionaryFile(dictFile);
	}

	private void readDictionaryFile(File dictFile) throws Exception {
		FileInputStream file = new FileInputStream(dictFile);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			String gerWord, gerArticle, nonGerWord;
			gerWord = cellIterator.next().getStringCellValue();
			gerArticle = cellIterator.next().getStringCellValue();
			try {
				nonGerWord = cellIterator.next().getStringCellValue();
				if (gerArticle.equals("")) {
					gerArticle = null;
				}
			} catch (Exception e) {
				nonGerWord = gerArticle;
				gerArticle = null;
			}
			DictionaryElement dictElt = new DictionaryElement(gerWord, gerArticle, nonGerWord);
			if (gerArticle == null) {
				eltsWithoutGerArticles.add(dictElt);
			} else {
				eltsWithGerArticles.add(dictElt);
			}
		}
		workbook.close();
		file.close();
	}

	private int randomIntFromInterval(int minInt, int maxInt) {
		return minInt + (int)(Math.random() * ((maxInt - minInt) + 1));
	}

	public DictionaryElement getRandomDictionaryElement() {
		List<DictionaryElement> allDictElts = new ArrayList<DictionaryElement>(
				eltsWithGerArticles);
		allDictElts.addAll(eltsWithoutGerArticles);
		return allDictElts.get(randomIntFromInterval(0, allDictElts.size() - 1));
	}

	public Set<String> getNPossibleTranslationsForElement(int numberOfTranslations, DictionaryElement dictElt,
			String translationDirection) {
		Set<String> possibleTranslations = new HashSet<String>();
		if (translationDirection.equals(Model.FROM_GERMAN)) {
			possibleTranslations.add(dictElt.getNonGerWord());
			if (dictElt.getGerArticle() == null) {
				while (possibleTranslations.size() < numberOfTranslations) {
					possibleTranslations.add(eltsWithoutGerArticles
							.get(randomIntFromInterval(0, eltsWithoutGerArticles.size() - 1))
							.getNonGerWord());
				}
			} else {
				while (possibleTranslations.size() < numberOfTranslations) {
					possibleTranslations.add(eltsWithGerArticles
							.get(randomIntFromInterval(0, eltsWithGerArticles.size() - 1))
							.getNonGerWord());
				}
			}
		} else if (translationDirection.equals(Model.TO_GERMAN)) {
			possibleTranslations.add(dictElt.getGerWord());
			if (dictElt.getGerArticle() == null) {
				while (possibleTranslations.size() < numberOfTranslations) {
					possibleTranslations.add(eltsWithoutGerArticles
							.get(randomIntFromInterval(0, eltsWithoutGerArticles.size() - 1))
							.getGerWord());
				}
			} else {
				while (possibleTranslations.size() < numberOfTranslations) {
					possibleTranslations.add(eltsWithGerArticles
							.get(randomIntFromInterval(0, eltsWithGerArticles.size() - 1))
							.getGerWord());
				}
			}
		}
		return possibleTranslations;
	}
}