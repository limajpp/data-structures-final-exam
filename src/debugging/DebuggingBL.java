package debugging;

import entities.HashMap;
import entities.LinkedList;
import entities.Word;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class DebuggingBL {
    public static void main(String[] args) {
        String filePath = "/home/bl/IdeaProjects/data-structures-final-exam/src/txts/Input.txt";
        String outputFilePath = "/home/bl/IdeaProjects/data-structures-final-exam/src/txts/Output.txt";
        String content = "";
        String contentOutput = "";
        int line = 1;
        HashMap hashMap = new HashMap();
        Scanner scanner = new Scanner(System.in);
        //Scanner para receber as palavras de busca
        String searchWords = scanner.nextLine();
        searchWords = searchWords.toLowerCase();
        System.out.println(searchWords);
        String[]splitedWords = searchWords.split(", ");


        try {
            content = Files.readString(Path.of(filePath), StandardCharsets.UTF_8);
            System.out.print(content);
        } catch (IOException e) {
            System.err.println("Failed in read file: " + e.getMessage());
        }

        Word word = null;

        for (int i = 0; i < content.length(); i++) {
            char currentChar = content.charAt(i);
            String currentString = content.substring(i,i+1);


            if (currentChar == ' ' || currentChar == '\n' || currentString.equals(",")) {
                if (word != null && !word.getWord().isEmpty()) {
                    hashMap.insert(word, line);
                    word = null;
                }
                if (currentChar == '\n') {
                    line++;
                }
            } else {
                if (word == null) {
                    word = new Word("");
                }
                word = new Word(word.getWord() + currentChar);
                word.setWord( word.getWord().toLowerCase());
            }
        }

        if (word != null && !word.getWord().isEmpty()) {
            hashMap.insert(word, line);
        }

        hashMap.print();

        //Bubble Sort to sort split words,Modificado para ser completo e menos eficiente
        boolean troca;

        do {
            troca = false; // Inicializa a flag como false no início de cada passada

            for (int j = 0; j < splitedWords.length - 1; j++) {
                if (splitedWords[j].compareTo(splitedWords[j + 1]) > 0) {
                    // Troca os elementos
                    String temp = splitedWords[j];
                    splitedWords[j] = splitedWords[j + 1];
                    splitedWords[j + 1] = temp;

                    troca = true; // Marca que houve troca
                }
            }

        } while (troca); // Continua enquanto houver trocas


        for (int i = 0; i < splitedWords.length; i++) {
            String wordToFind = splitedWords[i];
            LinkedList occurrences = hashMap.search(wordToFind);

            if (occurrences != null) {
                System.out.println("Índices de ocorrência de '" + wordToFind + "': " + occurrences.toString());
                contentOutput += wordToFind + ":" + occurrences.toString() + "\n";
            } else {
                System.out.println("Palavra '" + wordToFind + "' não encontrada.");
            }
        }
        //Escrita no arquivo txt
        try{Files.writeString(Path.of(outputFilePath),contentOutput, StandardOpenOption.WRITE);}
        catch (IOException e){
            System.err.println("Erro ao escrever no Arquivo "+e);
        }

    }
}
