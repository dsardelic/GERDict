package dict;

public class DictionaryElement {

    private String gerWord;
    private String gerArticle;
    private String nonGerWord;

    @SuppressWarnings("unused")
    private DictionaryElement() {
    }

    DictionaryElement(String gerWord, String gerArticle, String nonGerWord) {
        this.gerWord = gerWord;
        this.gerArticle = gerArticle;
        this.nonGerWord = nonGerWord;
    }

    public String getGerWord() {
        return gerWord;
    }

    public String getGerArticle() {
        return gerArticle;
    }

    public String getNonGerWord() {
        return nonGerWord;
    }
}
