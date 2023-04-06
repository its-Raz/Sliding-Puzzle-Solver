import java.util.Arrays;

public class Board {

    private Tile[] tiles;
    private Tile zeroTile;
    private int totalRows;//height
    private int totalColumns;//width
    /**FINAL VARIABLES DECLARATION*/
    private final static int ZERO = 0;
    private final static int ONE = 1;
    /**CONSTRUCTORS*/
    /*
     * The constructor get a 2D array with the data of the board and the numbers, and construct an array of tiles.
     * @param boardData - 2D array contains the data of the board and the numbers ordered as it was received.
     * */
    public Board(int[][] boardData) {
        int totalNumbers = boardData[ZERO][ZERO];
        this.totalRows = boardData[ZERO][ONE];
        this.totalColumns = totalNumbers / totalRows;
        tiles = new Tile[totalNumbers];
        for (int i = ZERO; i < totalNumbers; i++) {
            int currentNumber = boardData[ONE][i];
            int currentP = i;
            if (currentNumber != ZERO) {
                int wantedP = currentNumber - ONE;//because numbers should be orderded from small to big
                int[] matrixWantedSpot = Help.convertToMatrixSpot(wantedP, totalColumns);
                int wantedR = matrixWantedSpot[ZERO];
                int wantedC = matrixWantedSpot[ONE];
                tiles[i] = new Tile(currentNumber, wantedP, wantedR, wantedC, currentP);
            } else {
                zeroTile = new Tile(currentNumber, -ONE, -ONE, -ONE, currentP);
                tiles[i] = zeroTile;
            }
        }
    }
    /*
     * this constructor copy other board to avoid aliasing.
     * @param other - board object to copy.
     * */
    public Board(Board other)
    {
        int boardLength = other.tiles.length;
        Tile[] newTileBoard = new Tile[boardLength];
        for (int i = 0; i < boardLength; i++) //using Tile copy constructor to avoid aliasing
        {
            if (other.tiles[i].getValue() == ZERO) {
                this.zeroTile = new Tile(other.tiles[i]);
                newTileBoard[i] = this.zeroTile;
            }
            else {
                Tile copiedTile = new Tile(other.tiles[i]);
                newTileBoard[i] = copiedTile;
            }
            ;

        }
        this.tiles = newTileBoard;
        this.totalRows = other.totalRows;
        this.totalColumns = other.totalColumns;
    }
    /**METHODS*/

    /**GETTERS*/
    /*
     * return the zero object
     * @return - zeroTile
     * */
    public Tile getZeroTile() {
        return this.zeroTile;
    }
    /*
     * return the board width
     * @return - total columns of the board
     * */

    public int getBoardWidth() {
        return this.totalColumns;
    }
    /*
     * return the height of the board
     * @return - total rows of the board
     * */

    public int getBoardHeight() {
        return this.totalRows;
    }
    /*
     * return the tiles array
     * @return - tiles
     * */

    public Tile[] getTilesBoard() {
        return this.tiles;

    }
    /**END OF GETTERS*/
    /*
     * This method convert 2D matrix spot to 1D array spot
     * @param row - number of row
     * @param col - number of column
     * @return - the 1D spot of the tile in the array respectively to its matrix spot
     * */
    public int convertMatrixIndex(int row, int col) {
        return (row * this.totalColumns) + col;
    }
    /*
     * this method moving a tile in the tiles array, it swaps the desired tile with the zero tile.
     * @param TileToMove - the tile that has to be moved
     * */
    public void moveTile(Tile TileToMove) //vertical means change row, horizontal means change columns
    {
        int tileCurrenP = TileToMove.getCurrentPlace();//get the 1D spot to convert it to 2D then adding the
        int zeroCurrentP = this.zeroTile.getCurrentPlace();
        Tile movedTile = this.tiles[tileCurrenP];
        this.tiles[zeroCurrentP] = movedTile;
        this.tiles[tileCurrenP] = zeroTile;
        this.tiles[zeroCurrentP].setCurrentPlace(zeroCurrentP);//update the moved tile to 0 tile because this is where he goes
        this.zeroTile.setCurrentPlace(tileCurrenP); //update the zero location to the moved tile location
    }
    /*
     * this method overrides the equal method from object class, compare two board objects.
     * @param other - object to compare
     * @return boolean - true as the board are equal and false if not.
     * */
    @Override
    public boolean equals (Object other){
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }
    /*
     * overriding the hashCode method from the class object
     * @return the hash code of the object
     * */

    @Override
    public int hashCode () {
        return Arrays.deepHashCode(tiles);
    }
}
