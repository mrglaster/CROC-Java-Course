/**The Chessboard Square Class, realizing methods  described in the task for a Chess Square*/
public class ChessboardSquare {
    private int x;
    private int y;
    private final int BOARD_SIZE = 8;


    /**Constructor for the chess board square by 2 Origins: X and Y*/
    public ChessboardSquare(int nx, int ny){
        this.setX(nx);
        this.setY(ny);
    }



    /**Constructor for the chess board square by the origins string like a2, b7 and the same*/
    public ChessboardSquare(String origin){
        if (origin.length() != 2) {
            throw new IllegalArgumentException("Invalid coordinate string: " + origin + ". Expected length: 2");
        }
        String localOrigins = origin.toLowerCase();
        char xChar = localOrigins.charAt(0);
        char yChar = localOrigins.charAt(1);
        if (xChar < 'a' || xChar > 'h' || yChar < '1' || yChar > (char)BOARD_SIZE+'0') {
            throw new IllegalArgumentException("Invalid coordinate string: " + origin);
        }
        setX(xChar - 'a' + 1);
        setY(yChar - '1' + 1);
    }

    /**Checks if the input coordinate is valid*/
    private boolean isWrongOrigin(int coordinate){
        return coordinate < 1 || coordinate > BOARD_SIZE;
    }



    /**Setter for the X origin with the input value check*/
    public void setX(int nx) {
        if (isWrongOrigin(nx)) {
            throw new IllegalArgumentException("Invalid x coordinate: " + x);
        }
        this.x = nx;
    }

    /**Setter for the Y origin with the input check*/
    public void setY(int ny) {
        if (isWrongOrigin(ny)) {
            throw new IllegalArgumentException("Invalid y coordinate: " + y);
        }
        this.y = ny;
    }

    /**Prints the square's origins in the numeric format to the console*/
    public void printNumericOrigins(){
        System.out.println("(" + this.x + " ; " + this.y + " )");
    }

    /**Override of the method converting the Chess Board Square Class Element to the String Format*/
    @Override
    public String toString(){
        return String.valueOf((char)(this.x + 64)) + this.y;
    }
}
