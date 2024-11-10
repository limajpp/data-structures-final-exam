// Classe HashMap
package entities;

public class HashMap {

    int numberElements;
    Word[] allocation;

    public HashMap(){
        this.numberElements = 0;
        this.allocation = new Word[26];
    }

    public int funHash(Word word) {
        String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alfabet = alfabet.toLowerCase();
        int key = -1;
        for (int i = 0; i < alfabet.length(); i++) {
            if (word.getWord().charAt(0) == alfabet.charAt(i)) { // Acessa o primeiro caractere da palavra
                key = i;
                break;
            }
        }
        return key;
    }

    public void insert(Word word, int line) {
        int chave = funHash(word); // Calcula a chave com base na primeira letra

        if (chave == -1) {
            System.err.println("Palavra inválida para hash: " + word.getWord());
            return;
        }

        // Insere o índice na lista encadeada do objeto Word
        word.getIndex().insertAtEnd(line);
        this.allocation[chave] = word; // Armazena o objeto Word na posição calculada
        numberElements++;
    }

    public void print() {
        System.out.println("Chave\tPalavra +\tLista de Índices");
        for (int i = 0; i < allocation.length; i++) {
            if (allocation[i] != null) {
                System.out.print(i + "\t");
                allocation[i].print();
                allocation[i].getIndex().print();
            } else {
                System.out.println(i + "\t[ null ]");
            }
        }
    }
}
