import java.util.*;


/**
 *                  RUSSIAN
 *
 *  В виде строки задан относительный путь в файловой системе, в котором:
 *      — "." означает текущую директорию;
 *      — ".." означает родительскую директорию по отношению к текущей;
 *      — "/" используется в качестве разделителя директорий.
 *
 *  Реализовать функцию, выполняющую "нормализацию" заданного пути,
 *  то есть, удаляющую из него лишние директории с учетом переходов "." и "..".
 *
 *  Пример:
 *
 *  [in]
 *      "КРОК/task_5_2/src/./../../task_5_1/../../../мемы/котики"
 *  [out]
 *      ../мемы/котики"
 *
 *
 *                      English
 *
 *  A relative path in the file system is specified as a string, in which:
 *      — "." means the current directory;
 *      — ".." means the parent directory in relation to the current one;
 *      — "/" is used as a directory separator.
 *
 *  Implement a function that "normalizes" the given path,
 *  that is, removing unnecessary directories from it, taking into account the transitions "." And "..".
 *
 *  Example:
 *
 *  [in]
 *      "CROC/task_5_2/src/./../../task_5_1/../../../memes/cats"
 *
 * [out]
 *      ../memes/cats"
 *
 * */

public class Main {

    /**The function checks if the line is similar to paths in general. Supports paths in formats like:
     *     /path/to/directory
     *     /path/to/directory/
     *     /path/to/file.txt
     *     /да/я/умею/в/кириллицу/и/длинные/пути.да
     *     file.txt
     *     NO_EXTENSION_FILE
     *     */
    public static boolean isPathlikeString(String pathLine){
        if (pathLine.length() == 0) return false;
        String processedString = pathLine;
        processedString = processedString.replace(".", "hi").replace("_", "l");
        String pathRegex = "^/?([\\w\\d-А-Яа-я]+/?)*([\\w\\d-А-Яа-я]+\\.?)+$";
        return  processedString.matches(pathRegex);
    }

    /**Normalizes path by the following rule:
     *
     *      A relative path in the file system is specified as a string, in which:
     *          - "." means the current directory;
     *          - ".." means the parent directory in relation to the current one;
     *          - "/" is used as a directory separator.
     *
     *      For the realization we used Deque in LIFO mode:
     *          if we have .. - we remove the latest element
     *          else - we push it into the deque
     *  */
    public static String normalizePath(String path) {
        Deque<String> deque = new LinkedList<>();
        // Processing all elements of the path0
        for (String element : path.split("/")) {
            // If we need to go upper
            if (element.equals("..")) {
                deque.poll();
            // If current element isn't the "STAY HERE" symbol
            } else if (!element.equals(".")) {
                deque.push(element);
            }
        }
        // Joining the deque
        List<String> resultPath = new ArrayList<>(deque);
        Collections.reverse(resultPath);
        return '/' + String.join("/", resultPath);
    }



    /**The main function*/
    public static void main(String[] args) throws Exception {
        String pathLine = "";
        // Case when we don't get any arguments from command line on the program launch. Reading input via Scanner
        if (args.length == 0) {
            System.out.println("Write your path String:");
            Scanner sc = new Scanner(System.in);
            pathLine = sc.nextLine();
        } else {
            //If there is something to read from the args
            pathLine = args[0];
        }
            // If input string isn't a path-like string
            if (!isPathlikeString(pathLine)) {
                throw new Exception("Unsupported path format: " + pathLine + "\n Expected path like these: \n /path/to/directory \n /path/to/directory/ \n /path/to/file.txt \n file.txt \n MAKEFILE \n");
            }
            System.out.println(normalizePath(pathLine));
        }
    }
