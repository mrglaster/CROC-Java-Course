import InputChecker.InputChecker;

import java.io.*;

/**
 *              Russian
 * В текстовом файле слова могут быть разделены одним или несколькими пробелами, или символами перевода строки.
 * Необходимо реализовать программу, считающую количество слов в файле и выводящую результат на экран.
 * Путь к файлу задается первым аргументом командной строки (args[0]).
 * В случае, если аргумент не задан – кидать IllegalArgumentException.
 * При ошибке открытия файла сообщать об этом в консоль без вывода стектрейса.
 *
 * Пример:
 *
 * [in]
 * Забыл  Панкрат Кондратьевич домкрат,
 * А без домкрату ну не поднять на тракте трактор.
 * [out]
 * 13
 * */

/**
 * English
 * In a text file, words can be separated by one or more spaces or newlines.
 * It is necessary to implement a program that counts the number of words in a file and displays the result on the screen.
 * The path to the file is given as the first command line argument (args[0]).
 * If the argument is not set, throw an IllegalArgumentException.
 * If there is an error opening a file, report it to the console without outputting the stack trace.
 *
 * Example:
 *
 * [in]
 * Забыл  Панкрат Кондратьевич домкрат,
 * А без домкрату ну не поднять на тракте трактор.
 * [out]
 * 13
 * */






public class Main {
    /**Function demonstrating functions of the program*/
    public static void demoFunction() throws IOException {
        System.out.println("DEMO FUNCTION STARTS!\n");

        String[] fileNames = {
                "demofiles/test1.txt",
                "demofiles/test2.txt",
                "demofiles/test3.lol",
                "demofiles/test4.txt",
                "demofiles/test5.mysupertestfile",
        };

        InputChecker.checkInput(fileNames);


        for (int  i = 0; i < fileNames.length; i++){
            System.out.println("Current file: " + fileNames[i]);
            processFile(fileNames[i]);
            System.out.println();

        }
    }


    /**Counts amount of words in the text file*/
    public static void processFile(String fileName) throws IOException {
        int wordCount = 0;
        BufferedReader bReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = bReader.readLine()) != null) {
            if (line.length() != 0) {
                String[] words = line.trim().split("\\s+");
                for (int i = 0; i < words.length; i++) {
                }
                wordCount += words.length;
            }
        }
        System.out.println("Amount of Words: " + wordCount);
    }

    /**The main function. If there is no input, then the demo function will be used.*/
    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            InputChecker.checkInput(args);
        }
        else {
            demoFunction();
        }
    }
}