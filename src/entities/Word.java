// Classe Word
package entities;

public class Word {
    private String word;
    private LinkedList index;

    public Word(String word){
        this.word = word;
        this.index = new LinkedList();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public LinkedList getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Word: " + word + ", Index List: " + index.toString();
    }

    public void print(){
        System.out.print(word + "\t+");
    }
}
