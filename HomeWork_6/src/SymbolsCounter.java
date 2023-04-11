/*Imports section*/
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**Class containing methods for the counting of templates in the strings. */
    public class SymbolsCounter {

    //Variable enabling/disabling the DEBUG mode
    public static boolean DEBUG = false;

    /**Source version of the countRequiredSymbol function. Unoptimized, requires much time for long strings*/
    public static int countRequiredSymbol_Source(String inputLine, String template){
        int count = 0;
        for (int i=0; i < inputLine.length(); i++) {
            if (Matcher.match(String.valueOf(inputLine.charAt(i)), template)) {
                count++;
            }
        }
        return count;
    }

    /**Optimized version of the countRequiredSymbol function. This one uses multithreading  */
    public static int countRequiredSymbol_ExecutorService(String inputLine, String template) throws Exception {

        // Input checks
        if (inputLine.length() == 0) throw new Exception("Input Line Should be as minimum 1 symbol long! Got: 0");
        if (template.length() != 1) throw new Exception("Template must have lenght = 1. Got: " + template.length());

        //Getting amount of threads we can use
        int numThreads = Runtime.getRuntime().availableProcessors();
        if(DEBUG) System.out.println("Allowed amount of threads: " + numThreads);

        //Calculating the chunk size. The cрunk is one of several parts we split the input line to
        int chunkSize = (int) Math.ceil((double) inputLine.length() / numThreads);
        if (DEBUG) System.out.println("Size of the chunk: " + chunkSize);

        //Initialization of ExecutorService for the multithreading processing
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        //List for amount of the selected symbol in every separated chunk
        List<Future<Integer>> chunkSymbolsAmounts = new ArrayList<>();


        //Splitting the line to chunks
        for (int i = 0; i < inputLine.length(); i += chunkSize) {
            int start = i;
            int end = Math.min(start + chunkSize, inputLine.length());

            //Getting chunk for the processing
            String chunk = inputLine.substring(start, end);
            if (DEBUG) System.out.println("Processing chunk: " + chunk);

            //Running the calculations for current chunk via multithreading and putting the result into the chunkSymbolsAmounts List
            chunkSymbolsAmounts.add(executorService.submit(() -> {
                int countIn = 0;

                // Made like in the unoptimized version
                for (int j = 0; j < chunk.length(); j++) {
                    if (Matcher.match(String.valueOf(chunk.charAt(j)), template)) {
                        countIn++;
                    }
                }
                return countIn;
            }));
        }
        // We don't need this executor anymore! let's shut it down :)
        executorService.shutdown();

        // Enumerating List with selected symbol amount in every chunk and adding the result to returnable variable
        int resultCount = 0;
        for (Future<Integer> future : chunkSymbolsAmounts) {
            resultCount += future.get();
        }
        return resultCount;
    }


    /**Experimental version using the ParallelStream */
    public static int countRequiredSymbol_ParallelStream(String inputLine, String template) throws Exception {

        // Input checks
        if (inputLine.length() == 0) throw new Exception("Input Line Should be as minimum 1 symbol long! Got: 0");
        if (template.length() != 1) throw new Exception("Template must have lenght = 1. Got: " + template.length());

        //Initializing of counter variable
        AtomicInteger count = new AtomicInteger();

        //Converting string to CopyOnWriteArrayList  of chars
        CopyOnWriteArrayList<Character> chars = new CopyOnWriteArrayList<>(inputLine.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));

        //Enumerating list of chars and processing the match check
        chars.parallelStream().forEach((item)-> {
            // Made like in the source version
            if (Matcher.match(String.valueOf(item), template)) {
                count.getAndAdd(1);
            }
        });
        return count.get();
    }
}
