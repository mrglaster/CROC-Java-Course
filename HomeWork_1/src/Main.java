public class Main {

/*
   Russian

   Написать программу, которая выводит на экран числа от 1 до 100.
   При этом вместо чисел, кратных трем, программа должна выводить слово «Fizz»,
   а вместо чисел, кратных пяти — слово «Buzz».
   Если число кратно и 3, и 5, то программа должна выводить слово «FizzBuzz»

   English

   Write a program that displays numbers from 1 to 100.
   Instead of numbers that are multiples of three, the program should display the word "Fizz",
   and instead of multiples of five, the word "Buzz".
   If the number is a multiple of both 3 and 5, then the program should display the word "FizzBuzz"

  */

    //Define Amount of loop iterations as a constant
    private static final int LOOP_ITERATIONS_NUM = 100;

    //Define Variable for loop start
    private static final int LOOP_START_NUM = 1;


    //Define Fizz & Buzz String Constants
    private static final String FIZZ_STR = "Fizz";
    private static final String BUZZ_STR = "Buzz";


    /**Does the task described above*/
    public static void fbProcessor(){
        for (int i = LOOP_START_NUM; i <= LOOP_ITERATIONS_NUM; i++){

            //If no one of conditions works
            if(i % 3 != 0 && i % 5 != 0) {
                System.out.println(i);
                continue;
            }

            //Build the string if current number is divisible by 3, 5 or both
            String oWord = ((i % 3 == 0) ? FIZZ_STR : "") + ((i % 5 == 0) ? BUZZ_STR : "");
            System.out.println(oWord);
        }
    }

    /**The main function*/
    public static void main(String[] args) {
        fbProcessor();
    }
}
