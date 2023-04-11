public class Demonstrator {
    
    //Default demo string with length=77
    public static String testString = "hfdhgfdlkgjfiogvogfjgfgiodgidgdjofigjdoigjdgjdfoigdfiojgdjgodijgdfiogodifgkgj";

    /**Demonstrates the work of the source function on the testString (length = 72). Prints time of the work*/
    static void demonstrateSource(){
        System.out.println("Using the source algorithm: ");
        long startTime = System.nanoTime();
        System.out.println("Length of the String: " + testString.length());
        System.out.println("Amount of Symbols h: " + SymbolsCounter.countRequiredSymbol_Source(testString, "h"));
        long endTime = System.nanoTime();
        System.out.println("Elapsed time: " + (endTime - startTime) / 1000000 / 1000.0 + " s");
    }

    /**Demonstrates ExecutorService-based algorithm on the default data*/
    static void demoeOptimized() throws Exception {
        demoOptimizedCustomLine(testString, "h");
    }

    /**Runs the optimized ExecutorService-based algorithm's demo for the custom strung & template*/
    static void demoOptimizedCustomLine(String customLine, String template) throws Exception {
        System.out.println("\nUsing an optimized algorithm: ");
        long startTime = System.nanoTime();
        System.out.println("Length of the String: " + customLine.length());
        System.out.println("Looking for symbol: " + template);
        System.out.println("Amount of symbols '" + template + "': " + SymbolsCounter.countRequiredSymbol_ExecutorService(customLine, template));
        long endTime = System.nanoTime();
        System.out.println("Elapsed time: " + (endTime - startTime) / 1000000 / 1000.0 + " s");
    }


    /**Demonstrates usage of ParallelStream algorithm with default values*/
    static void demoParallelStream(){
        demoParallelStreamCustomLine(testString, "h");
    }

    /**Demonstrates usage of ParallelStream algorithm with custom values */
    static void demoParallelStreamCustomLine(String inputLine, String templateChar){
        System.out.println("\nUsing an ParallelStream algorithm: ");
        long startTime = System.nanoTime();
        System.out.println("Length of the String: " + inputLine.length());
        System.out.println("Looking for symbol: " + templateChar);
        try {
            System.out.println("Amount of symbols '" + templateChar + "': " + SymbolsCounter.countRequiredSymbol_ParallelStream(inputLine, templateChar));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long endTime = System.nanoTime();
        System.out.println("Elapsed time: " + (endTime - startTime) / 1000000 / 1000.0 + " s\n");
    }

    /**Global demonstration for all the made Math-finding algorithms*/
    static void globalDemonstration() throws Exception {

        //Initial demo. Length = 77
        demonstrateSource();
        demoeOptimized();
        demoParallelStream();

        //Message about the stakes raising
        System.out.println("\nLet's raise the stakes!\n");
        System.out.println("We increase length of the test string via adding (testString + testString) !\n");

        //ParallelStream & ExecutorService-based algorithms compare
        //Length = 154
        String currentTestString = testString + testString;
        demoOptimizedCustomLine(currentTestString, "h");
        demoParallelStreamCustomLine(currentTestString, "h");

        //Length = 231
        currentTestString += testString;
        demoOptimizedCustomLine(currentTestString, "g");
        demoParallelStreamCustomLine(currentTestString, "g");

        //Length = 308
        currentTestString+=testString;
        demoOptimizedCustomLine(currentTestString, "f");
        demoParallelStreamCustomLine(currentTestString, "f");
    }

}
