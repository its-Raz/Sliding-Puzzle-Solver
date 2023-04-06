public class Help {
    /** FINAL VARIABLES DECLARATION **/
    private final static int ZERO = 0;
    private final static int ONE = 1;
    private final static int TWO = 2;
    /** METHODS **/
    /*
     * The method get the string of the board and generate an array that contains data on the board and an array of numbers
     * @param str - the string that represent the board
     * @return - a 2D array that contains data on the board and the numbers that the board contains order respectively like it was received.
     * */
    public static int[][] convertToNumbers(String str) {
        int[] arrayOfAll = new int[str.length()];
        int totalNumbers = ZERO;
        int rows = ONE;
        //int columns = 1;
        String currentChar;
        int currentNum = 0;
        for (int i = ZERO; i < str.length(); i++) {
            currentChar = str.substring(i, i + ONE);
            if (!(currentChar.equals(" "))) {
                if (!(currentChar.equals("|"))) {
                    currentNum = Integer.valueOf(currentChar);
                    while (((i + 1) < str.length()) && (str.charAt(i + ONE) != (' ')) && (str.charAt(i + ONE) != '|')) {
                        currentChar = str.substring(i+ONE, i + TWO);
                        currentNum = currentNum * 10 + Integer.valueOf(currentChar);
                        i++;
                    }
                    arrayOfAll[totalNumbers] = currentNum;
                    totalNumbers++;
                }
                if (currentChar.equals("|")) {
                    rows++;
                }

            }
        }
        int[] arrayNumbers = new int[totalNumbers];
        int zeroIndex = -ONE;
        for (int i = ZERO; i < totalNumbers; i++) {
            arrayNumbers[i] = arrayOfAll[i];
            if (arrayNumbers[i] == ZERO) {
                zeroIndex = i;
            }
        }
        int width = totalNumbers/rows;
        int[][] data = new int[TWO][];
        data[ZERO] = new int[3];//0-numbers,1-rows,2 zerolocation
        data[ONE] = arrayNumbers;
        data[ZERO][ZERO] = totalNumbers;
        data[ZERO][ONE] = rows;
        data[ZERO][2] = (zeroIndex / width)+ONE;
        return data;
    }
    /*this method convert 1D array spot to 2D Matrix spot
     * @param oneDimSpot - the location of the tile in the 1D array
     * @param width - the total columns of the board
     * @return - int array where the number of the row is in the first place of the array and the columns is in the second.
     * */
    public static int[] convertToMatrixSpot(int oneDimSpot, int width) {
        int[] matrixSize = new int[TWO];
        matrixSize[ZERO] = oneDimSpot / (width);//when matrix indexes starts from 0
        matrixSize[ONE] = oneDimSpot % (width);//when matrix indexes starts from 0
        return matrixSize;
    }
}
