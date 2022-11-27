import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileAway {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String line;
        ArrayList<String> lines = new ArrayList<>();

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                //if the user selects a file
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                //Path path = Paths.get(""); replace every "/" with "\\"

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                System.out.println("\n"+ selectedFile);

                int lineNumber = 0;
                while(reader.ready()){
                    line = reader.readLine();
                    lines.add(line);
                    lineNumber++;
                }
                System.out.println("\nThe amount of lines in the file is: "+lineNumber);

                String currentLine;
                String currentWord;
                String[] currentArray;
                int wordCount = 0;
                String[] letterBreaker;
                int characterCount = 0;
                for(int i = 0; i < lineNumber; i++){
                    currentLine = lines.get(i);
                    currentArray = currentLine.split(", ");
                    wordCount = wordCount + currentArray.length;
                    for(int x = 0; x < currentArray.length; x++){
                        currentWord = currentArray[x];
                        letterBreaker = currentWord.split("");
                        characterCount = characterCount + letterBreaker.length;
                    }
                }
                System.out.println("The amount of words in the file is: "+wordCount);
                System.out.println("The amount of characters in the file is: "+characterCount);

                reader.close();
                System.out.println("\nFile read successfully!");
            } else {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again");
                System.exit(0);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
