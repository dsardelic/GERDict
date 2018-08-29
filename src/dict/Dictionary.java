package dict;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Dictionary {

	public static final String DER = "der";
	public static final String DIE = "die";
	public static final String DAS = "das";

	private List<DictionaryElement> eltsWithGerArticles;
	private List<DictionaryElement> eltsWithoutGerArticles;

	@SuppressWarnings("unused")
	private Dictionary() {
	}
	
	public Dictionary(File dictFile) throws Exception {
		this.eltsWithGerArticles = new ArrayList<>();
		this.eltsWithoutGerArticles = new ArrayList<>();
		readDictionaryFile(dictFile);
	}

	private void readDictionaryFile(File dictFile) throws Exception {
		FileInputStream file = new FileInputStream(dictFile);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		Iterator<Row> rows = workbook.getSheetAt(0).rowIterator();
		while (rows.hasNext()) {
			XSSFRow row = (XSSFRow) rows.next();
			String gerWord = row.getCell(0, Row.CREATE_NULL_AS_BLANK).toString();
			String gerArticle = row.getCell(1, Row.CREATE_NULL_AS_BLANK).toString();
			String nonGerWord = row.getCell(2, Row.CREATE_NULL_AS_BLANK).toString();
			if (gerArticle.equals("")) {
				gerArticle = null;
			}
			List<DictionaryElement> elts = (gerArticle == null ? eltsWithoutGerArticles : eltsWithGerArticles);
			elts.add(new DictionaryElement(gerWord, gerArticle, nonGerWord));
		}
		workbook.close();
		file.close();
	}

	public boolean containsWord(String word) {
		if (word.indexOf("(" + DER + ")") >= 0 || word.indexOf("(" + DIE + ")") >= 0 || word.indexOf("(" + DAS + ")") >= 0) {
			for (DictionaryElement dictionaryElement : eltsWithGerArticles) {
				if (word.equals(dictionaryElement.getGerWord() + " (" + dictionaryElement.getGerArticle() + ")")) {
					return true;
				}
			}
			return false;
		} else {
			for (DictionaryElement dictionaryElement : eltsWithoutGerArticles) {
				if (word.equals(dictionaryElement.getGerWord())) {
					return true;
				}
			}
			return false;
		}
	}

	private int randomIntFromInterval(int minInt, int maxInt) {
		return minInt + (int) (Math.random() * ((maxInt - minInt) + 1));
	}

	public DictionaryElement getRandomDictionaryElement() {
		List<DictionaryElement> allDictElts = new ArrayList<>(eltsWithGerArticles);
		allDictElts.addAll(eltsWithoutGerArticles);
		return allDictElts.get(randomIntFromInterval(0, allDictElts.size() - 1));
	}

	public Set<String> getNPossibleTranslationsForElement(DictionaryElement dictElt, int numberOfTranslations,
			boolean isTranslateFromGerman) {
		Set<String> possibleTranslations = new HashSet<>();
		possibleTranslations.add(isTranslateFromGerman ? dictElt.getNonGerWord() : dictElt.getGerWord());
		List<DictionaryElement> elts = dictElt.getGerArticle() == null ? eltsWithoutGerArticles : eltsWithGerArticles;
		while (possibleTranslations.size() < numberOfTranslations) {
			DictionaryElement randomElt = elts.get(randomIntFromInterval(0, elts.size() - 1));
			possibleTranslations.add(isTranslateFromGerman ? randomElt.getNonGerWord() : randomElt.getGerWord());
		}
		return possibleTranslations;
	}
}
