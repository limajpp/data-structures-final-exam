package debugging;

import entities.HashMap;
import entities.LinkedList;
import entities.Word;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Scanner;

public class DebuggingBL {
    public static void main(String[] args) {
        String filePath = "/home/bl/IdeaProjects/data-structures-final-exam/src/txts/Input.txt";
        String content = "";
        int line = 1;
        HashMap hashMap = new HashMap();
        Scanner scanner = new Scanner(System.in);
        String searchWords = scanner.next();
        searchWords = searchWords.toLowerCase();
        String[]splitedWords = searchWords.split(",");


        try {
            content = Files.readString(Path.of(filePath), StandardCharsets.UTF_8);
            System.out.print(content);
        } catch (IOException e) {
            System.err.println("Failed in read file: " + e.getMessage());
        }

        Word word = null;

        for (int i = 0; i < content.length(); i++) {
            char currentChar = content.charAt(i);

            if (currentChar == ' ' || currentChar == '\n') {
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



        for (int i = 0; i < splitedWords.length; i++) {
            String wordToFind = splitedWords[i];
            LinkedList occurrences = hashMap.search(wordToFind);

            if (occurrences != null) {
                System.out.println("Índices de ocorrência de '" + wordToFind + "': " + occurrences.toString());
            } else {
                System.out.println("Palavra '" + wordToFind + "' não encontrada.");
            }
        }
    }
}
