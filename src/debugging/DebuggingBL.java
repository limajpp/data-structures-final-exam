package debugging;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class DebuggingBL {
    public static void main(String[] args) {
        String filePath = "/home/bl/IdeaProjects/data-structures-final-exam/src/txts/Input.txt";
        try {
            String content = Files.readString(Path.of(filePath), StandardCharsets.UTF_8);
            System.out.print(content);
        } catch (IOException e) {
            System.err.println("Failed in read file: " + e.getMessage());
        }
    }
}
