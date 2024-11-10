package entities;

public class Word {
    private String word;
    private LinkedList index;

    public Word(String word){
        this.word = word;
        this.index = new LinkedList();
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public LinkedList getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Word: " + word + ", Index List: " + index.toString();
    }
}
