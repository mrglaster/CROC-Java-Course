/**                        RUSSIAN
 Определить класс, описывающий координаты шахматной клетки.
 Данные класса: компоненты x и y, отсчитываемые от левого нижнего угла.
 Все методы, позволяющие установить координаты, в том числе и конструкторы,
 должны проверять корректность аргументов и генерировать IllegalArgumentException в случае ошибочных значений.
 */

/***                        ENGLISH
 * Define a class that describes the coordinates of a chess cell.
 * Class data: x and y components counted from the lower left corner.
 * All methods that allow you to set coordinates, including constructors,
 * should validate the arguments and throw an IllegalArgumentException on invalid values.
 */


public class Main {
    public static void main(String[] args) {

        System.out.println("\nNOTE: IN THIS VERSION ALL ORIGINS START FROM (1;1) ORIGIN. IT'S A1 HERE! \n");

        //Demo variables initialization
        ChessboardSquare numericSquare = new ChessboardSquare(1, 1);
        ChessboardSquare stringSquare = new ChessboardSquare("e4");

        //Numeric version demonstration
        System.out.println("\n    DEMONSTRATING THE NUMERIC VERSION\n");
        System.out.print("Numeric origins: ");
        numericSquare.printNumericOrigins();
        System.out.println("toString Demo: " + numericSquare + '\n');


        //String based version demo
        System.out.println("    DEMONSTRATING THE STRING BASED VERSION\n");
        System.out.print("Numeric origins: ");
        stringSquare.printNumericOrigins();
        System.out.println("toString Demo: " + stringSquare +'\n');
    }
}
