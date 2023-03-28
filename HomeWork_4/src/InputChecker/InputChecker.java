package InputChecker;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputChecker {

    /**
     * Reads first 10  bytes of the input file and checks if they correspond to
     * the UTF-8 Encoding
     * */
    private static boolean isTextFile(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            byte[] buffer = new byte[4096];
            try {
                Charset.availableCharsets().get("windows-1251").newDecoder()
                        .decode(ByteBuffer.wrap(buffer));
                return true;
            } catch (CharacterCodingException e) {
                return false;
            }
        }
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
