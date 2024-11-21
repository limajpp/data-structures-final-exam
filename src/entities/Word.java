package entities;

public class Word {
    private String word;
    private final LinkedList OCCURRENCES;

    public Word(String word){
        this.word = word;
        this.OCCURRENCES = new LinkedList();
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public LinkedList getOCCURRENCES() { return OCCURRENCES; }

    @Override
    public String toString() {
        return "Word: " + word + ", Index List: " + OCCURRENCES;
    }
}
