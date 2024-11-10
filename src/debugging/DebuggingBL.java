// Classe DebuggingBL
package debugging;

import entities.HashMap;
import entities.Word;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class DebuggingBL {
    public static void main(String[] args) {
        String filePath = "/home/bl/IdeaProjects/data-structures-final-exam/src/txts/Input.txt";
        String content = "";
        int line = 1;
        HashMap hashMap = new HashMap();

        try {
            content = Files.readString(Path.of(filePath), StandardCharsets.UTF_8);
            System.out.print(content);
        } catch (IOException e) {
            System.err.println("Failed in read file: " + e.getMessage());
        }

        Word word = null; // Declara o objeto fora do loop

        for (int i = 0; i < content.length(); i++) {
            char currentChar = content.charAt(i);

            if (currentChar == ' ' || currentChar == '\n') {
                if (word != null && !word.getWord().isEmpty()) { // Verifica se há uma palavra
                    word.setWord(word.getWord().toLowerCase());
                    hashMap.insert(word, line);
                    word = null; // Reinicia o objeto Word para a próxima palavra
                }
                if (currentChar == '\n') {
                    line++;
                }
            } else {
                if (word == null) {
                    word = new Word(""); // Cria um novo objeto Word para a próxima palavra
                }
                word.setWord(word.getWord() + currentChar);
            }
        }

        // Insere a última palavra se houver
        if (word != null && !word.getWord().isEmpty()) {
            hashMap.insert(word, line);
        }

        hashMap.print();
    }
}