package InputChecker;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**Class containing functions for the input data checks*/
public class InputChecker {

    /**Checks if the input file has text to read */
    private static boolean isTextFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String sCurrentLine =  br.readLine();
            // If there is something to read
            if (sCurrentLine == null || sCurrentLine.contains("�")) return false;

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**Validates input files. Supports Multiple files input*/
    public static void checkInput(String[] args) throws IOException {

        // Checks if there is any input data
        // For the current program's version this check has no sense:
        // if there is no input, we use the demo function.
        // But in other case this check is required
        if (args.length == 0){
            throw new IllegalArgumentException("Any input expected!");
        }
        for (int i = 0; i < args.length; i++){
            Path fileName = Paths.get(args[i]);
            // File existence check
            if(!Files.exists(fileName)) {
                throw new FileNotFoundException("File " + args[0] + " doesn't exist!");
            }

            // Checks if the input file is a directory
            if (Files.isDirectory(fileName)){
                throw new IllegalArgumentException("Expected file. Got: Directory");
            }

            // Checks if it's possible to write the file
            if (!Files.isReadable(fileName)){
                throw new  IllegalArgumentException("Unable to read the file!");
            }

            if (!isTextFile(fileName.toString())){
                System.out.println(fileName.toString());
                throw new IllegalArgumentException("Input file is not a text file! Unable to process!");
            }
        }
    }
}
