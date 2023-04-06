public class Action {
    private Tile tile;
    private Direction direction;



    /*
     *constructor of a single action
     * @param currentTile- a single tile
     * @param currentDirection- the direction of the tile
     */
    public Action (Tile currentTile, Direction currentDirection) {
        this.tile = currentTile;
        this.direction = currentDirection;
    }
    public Action ()
    {
        this.tile=null;
        this.direction=Direction.UP;
    }
    /*
     *gets the pointer of the current tile
     * @return - the pointer of the current tile
     */
    public Tile getCurrentTile (){
        return this.tile;
    }

    /*
     *gets the value of the current direction
     * @return - the value of the current direction
     */
    public Direction getCurrentDirection (){
        return this.direction;
    }
    /*
     *calculates the direction to the previous action
     * @return the direction that leads to the previous action
     */
    public Action convertPrevious()
    {
        Direction newDirection;
        switch (this.direction) {
            case UP:
                newDirection = Direction.DOWN ;
                break;
            case DOWN:
                newDirection = Direction.UP ;
                break;
            case LEFT:
                newDirection = Direction.RIGHT ;
                break;
            default:
                newDirection = Direction.LEFT ;
        }
        return new Action(this.tile,newDirection);
    }

    /*
     *creates a string that presents an action
     * @return- the string to print
     */
    @Override
    public String toString() {
        String str;
        switch (this.direction) {
            case UP:
                str = ("Move " + tile.getValue() + " up");
                break;
            case DOWN:
                str = ("Move " + tile.getValue() + " down");
                break;
            case LEFT:
                str = ("Move " + tile.getValue() + " left");
                break;
            default:
                str = ("Move " + tile.getValue() + " right");
        }
        return str;
    }

    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof Action)) {
            return false;
        }
        Action otherAction = (Action) other;
        return otherAction.tile.equals(this.tile) && otherAction.direction == this.direction;
    }
}
