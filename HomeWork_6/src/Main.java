/*
*                          Russian
*
*    Посчитать количество определённых символов в строке, используя метод
*    Matcher.match(String str, String character)
*    Метод возвращает true в случае обнаружения в строке str символа character.
*
*    Параметры:
*    str – строка в которой ищется символ
*    character – искомый символ
*
*    Исходная функция countRequiredSymbol_Source работает неэффективно.
*    Необходимо переписать её таким образом, чтобы эффективность возрасла.
*    При этом необходимо использовать метод Matcher.match
*    Метод match менять нельзя
*
*
*                           English
*
*    Count the number of certain characters in a string using the method
*    Matcher.match(String str, String character)
*    The method returns true if the character character is found in the string str.
*
*    Parameters:
*    str - the string in which the character is searched
*    character - the character you are looking for
*
*    The original countRequiredSymbol_Source function is inefficient.
*    It is necessary to rewrite it in such a way that efficiency has increased.
*    In this case, you must use the Matcher.match method
*    The match method cannot be changed
*
* */

/**The Main class*/
public class Main {

    /**The main function. Runs the code. */
    public static void main(String[] args) throws Exception {
        if (args.length == 0){
            System.out.println("\nNo command line arguments found! Let's run the demonstration function!\n");
            Demonstrator.globalDemonstration();
        }
        else if (args.length == 1){
                System.out.println("As minimum 2 arguments required! Got: 1");
        } else {
            String inputLine = args[0];
            String template = args[1];
            Demonstrator.demoOptimizedCustomLine(inputLine, template);
            Demonstrator.demoParallelStreamCustomLine(inputLine, template);
        }
    }
}