package entities;

public class HashMap {

    int numberElements;
    BinarySearchTree[] allocation;

    public HashMap(){
        this.numberElements = 0;
        this.allocation = new BinarySearchTree[26];
    }

    public int funHash(String word) {
        char firstChar = word.toLowerCase().charAt(0);
        return firstChar - 'a';  // Calcula a posição da letra no alfabeto
    }

    public void insert(Word word, int line) {
        int chave = funHash(word.getWord()); // Calcula a chave com base na primeira letra

        if (chave < 0 || chave >= 26) {
            System.err.println("Palavra inválida para hash: " + word.getWord());
            return;
        }

        // Se não houver uma árvore na posição, cria uma nova
        if (allocation[chave] == null) {
            allocation[chave] = new BinarySearchTree();
        }

        // Insere a palavra na árvore binária de busca da posição especificada
        allocation[chave].insert(word, line);
        numberElements++;
    }

    public void print() {
        System.out.println("Chave\tPalavra\t\tLista de Índices");
        for (int i = 0; i < allocation.length; i++) {
            if (allocation[i] != null) {
                System.out.print(i + "\t");
                allocation[i].print(); // Print da árvore para exibir palavras e índices
            } else {
                System.out.println(i + "\t[ null ]");
            }
        }
    }
    public LinkedList search(String wordToFind) {
        int chave = funHash(wordToFind); // Calcula a chave com base na primeira letra

        if (chave < 0 || chave >= 26 || allocation[chave] == null) {
            System.out.println("Palavra não encontrada: " + wordToFind);
            return null;
        }

        // Busca a palavra na árvore binária de busca correspondente
        Word foundWord = allocation[chave].search(new Word(wordToFind));//Como recebe uma String, instancia uma Word e procura na Tree.

        if (foundWord != null) {
            return foundWord.getOCCURRENCES();  // Retorna a lista de índices
        } else {
            System.out.println("Palavra não encontrada: " + wordToFind);
            return null;
        }
    }
}
