package debugging;

import entities.HashMap;
import entities.LinkedList;
import entities.Word;
import exceptions.FileReadFailException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

public class DebuggingBL {
    public static void main(String[] args) {
        // Paths para os arquivos '.txt' usados na execução
        String inputWordsFilePath = "C:\\Users\\joaop\\IdeaProjects\\data-structures-final-exam\\src\\txts\\InputWords.txt";
        String inputFilePath = "C:\\Users\\joaop\\IdeaProjects\\data-structures-final-exam\\src\\txts\\Input.txt";
        String outputFilePath = "C:\\Users\\joaop\\IdeaProjects\\data-structures-final-exam\\src\\txts\\Output.txt";

        // Strings que armazenam o conteúdo do arquivo de entrada (Input.txt) e saída (Output.txt)
        String contentInput;
        StringBuilder contentOutput = new StringBuilder();

        // Variável que armazena o número da linha atual no arquivo de entrada
        int line = 1;

        HashMap hashMap = new HashMap();

        // Variável para armazenar o conteúdo do arquivo 'InputWords.txt' (palavras a serem buscadas)
        String searchWords;

        // Array para armazenar as palavras separadas da variável 'searchWords'
        String[] splitedWords;

        try {
            // Lê o conteúdo do arquivo 'InputWords.txt' e converte para letras minúsculas
            searchWords = Files.readString(Path.of(inputWordsFilePath), StandardCharsets.UTF_8);
            searchWords = searchWords.toLowerCase();
            System.out.printf("Palavras da busca: %s%n%n", searchWords);

            // Divide o conteúdo do arquivo em um array de palavras
            splitedWords = searchWords.split(", ");
        } catch (IOException | FileReadFailException exception) {
            throw new FileReadFailException("Failed in read file 'InputWords.txt': " + exception.getMessage());
        }

        try {
            // Lê o conteúdo do arquivo de entrada (Input.txt)
            contentInput = Files.readString(Path.of(inputFilePath), StandardCharsets.UTF_8);
            System.out.printf("Conteúdo do input:%n%s%n%n", contentInput);
        } catch (IOException | FileReadFailException exception) {
            throw new FileReadFailException("Failed in read file 'Input.txt': " + exception.getMessage());
        }

        Word word = null;

        for (int i = 0; i < contentInput.length(); i++) {
            char currentChar = contentInput.charAt(i);
            String currentString = contentInput.substring(i, i + 1); // String correspondente ao caractere atual

            // Verifica se o caractere atual é um delimitador de palavra
            if (currentChar == ' ' || currentChar == '\n' || currentString.equals(",") || currentString.equals(".")) {
                if (word != null && !word.getWord().isEmpty()) {
                    hashMap.insert(word, line);
                    word = null; // Reinicia a variável para a próxima palavra
                }
                if (currentChar == '\n') {
                    // Incrementa o número da linha ao encontrar uma quebra de linha
                    line++;
                }
            } else {
                // Constrói a palavra caractere por caractere
                if (word == null) {
                    word = new Word("");
                }
                word = new Word(word.getWord() + currentChar);
                word.setWord(word.getWord().toLowerCase()); // Converte a palavra para letras minúsculas
            }
        }

        // Insere a última palavra no HashMap, se aplicável
        if (word != null && !word.getWord().isEmpty()) {
            hashMap.insert(word, line);
        }

        // Imprime o conteúdo do HashMap
        System.out.println();
        hashMap.print();

        // Ordena as palavras do array usando Bubble Sort
        boolean troca;
        do {
            troca = false;

            for (int j = 0; j < splitedWords.length - 1; j++) {
                if (splitedWords[j].compareTo(splitedWords[j + 1]) > 0) {
                    // Troca as palavras adjacentes se estiverem fora de ordem
                    String temp = splitedWords[j];
                    splitedWords[j] = splitedWords[j + 1];
                    splitedWords[j + 1] = temp;

                    troca = true;
                }
            }
        } while (troca);

        // Busca as ocorrências de cada palavra ordenada no HashMap
        for (String wordToFind : splitedWords) {
            LinkedList occurrences = hashMap.search(wordToFind); // Busca a lista de ocorrências da palavra

            if (occurrences != null) {
                System.out.println("Índices de ocorrência de '" + wordToFind + "': " + occurrences);
                contentOutput.append(wordToFind).append(":").append(occurrences).append("\n");
            } else {
                System.out.println("Palavra '" + wordToFind + "' não encontrada.");
            }
        }

        // Escreve o conteúdo gerado no arquivo de saída (Output.txt)
        try {
            Files.writeString(Path.of(outputFilePath), contentOutput, StandardOpenOption.WRITE);
        } catch (IOException e) {
            // Exibe mensagem de erro em caso de falha na escrita do arquivo
            System.err.println("Erro ao escrever no Arquivo " + e);
        }
    }
}
