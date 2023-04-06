public class Tile {

    private int value;
    private int wantedPlace;
    private int wantedRow;
    private int wantedCol;
    private int currentPlace;


    /*
     *constructor of a single tile
     * @param valueOfTile - the value of a single tile
     * @param wantedP - the index of the goal of the tile
     * @param wantedR - the row of the goal of the tile
     * @param wantedC - the column of the goal of the tile
     * @param currentP - the index of the current place of the tile
     */
    public Tile(int valueOfTile, int wantedP, int wantedR, int wantedC, int currentP){
        this.value = valueOfTile;
        this.wantedPlace = wantedP;
        this.wantedRow = wantedR;
        this.wantedCol = wantedC;
        this.currentPlace = currentP;
    }

    /*
     *copy constructor of a single tile
     * @param other - the tile to copy
     */
    public Tile(Tile other) //copy constructor
    {
        this.value = other.value;
        this.wantedPlace = other.wantedPlace;
        this.wantedRow = other.wantedRow;
        this.wantedCol = other.wantedCol;
        this.currentPlace = other.currentPlace;
    }

    /*
     *gets the value of the tile
     * @return - the value of the tile
     */
    public int getValue (){
        return this.value;
    }

    /*
     *gets the index of the goal of the tile
     * @return - the index of the goal of the tile
     */
    public int getWantedPlace (){
        return this.wantedPlace;
    }

    /*
     *gets the index of the current place of the tile
     * @return - the index of the current place
     */
    public int getCurrentPlace (){
        return this.currentPlace;
    }

    /*
     *updates the index of the current place of the tile
     * @param currentP - the new value of the current place to update
     */
    public void setCurrentPlace (int currentP){
        this.currentPlace = currentP;
    }

    /*
     *calculates the distance between the goal to a tile
     * @param row - row index of a tile
     * @param column - column index of a tile
     * @return - the distance between the goal to a tile
     */
    public int getDistance(int row, int column){
        return absCalculation(this.wantedRow, row)+absCalculation(this.wantedCol, column);
    }

    /*
     *calculates the difference between two numbers
     * @param a - first number
     * @param b - second number
     * @return - the difference between two numbers
     */
    private int absCalculation(int a, int b){
        if(a > b)
            return a-b;
        return b-a;
    }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
