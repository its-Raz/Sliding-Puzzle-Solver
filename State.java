public class State {

    private Board board;


    /*
     *constructor of a single state
     * @param currentBoard - the current board
     */
    public State(Board currentBoard){
        this.board = currentBoard;
    }
    /*
     *copy constructor of a single state
     * @param other - the state to copy
     */
    public State(State other)
    {
        Board newBoard = new Board (other.getBoard());
        this.board=newBoard;
    }

    /*
     *checks if the current state is the goal
     * @return - true if the state is the goal, otherwise - false
     */
    public boolean isGoal(){
        int maxValue = this.board.getTilesBoard().length - 1;//the max value
        if((this.board.getTilesBoard()[maxValue - 1].getValue()) == maxValue) {
            //the max value is in place
            for (int i = 0; i < maxValue; i++) {
                if (!((i + 1) == (this.board.getTilesBoard()[i].getValue()))) {
                    return false;
                }
            }
        }else{
            //the max value is not in place
            return false;
        }
        //all tiles are in place
        return true;
    }

    /*
     *creates an array of available actions
     * @param action - what tile to move in what direction
     * @return - the new state
     */
    public Action[] actions(){
        int width = this.board.getBoardWidth();
        int height = this.board.getBoardHeight();
        Action[] helpArray = new Action[4];
        int indexZero = this.board.getZeroTile().getCurrentPlace();
        int[] matrixZero = Help.convertToMatrixSpot(indexZero,width);
        int counter=0;
        if(matrixZero[0] +1 < height){
            //zero can go down, so a tile can go up
            int tileIndex = this.board.convertMatrixIndex(matrixZero[0] +1, matrixZero[1]);
            Tile tileToMove = this.board.getTilesBoard()[tileIndex];
            helpArray[counter] = new Action(tileToMove,Direction.UP);
            counter++;

        }
        if(matrixZero[0] -1 >= 0  ){
            //zero can go up, so a tile can go down
            int tileIndex = this.board.convertMatrixIndex(matrixZero[0] -1, matrixZero[1]);
            Tile tileToMove = this.board.getTilesBoard()[tileIndex];
            helpArray[counter] = new Action(tileToMove,Direction.DOWN);
            counter++;
        }
        if(matrixZero[1] +1 < width){
            //zero can go right, so a tile can go left
            int tileIndex = this.board.convertMatrixIndex(matrixZero[0], matrixZero[1] +1);
            Tile tileToMove = this.board.getTilesBoard()[tileIndex];
            helpArray[counter] = new Action(tileToMove,Direction.LEFT);
            counter++;
        }
        if(matrixZero[1] -1 >= 0){
            //zero can go left, so a tile can go right
            int tileIndex = this.board.convertMatrixIndex(matrixZero[0], matrixZero[1] -1);
            Tile tileToMove = this.board.getTilesBoard()[tileIndex];
            helpArray[counter] = new Action(tileToMove, Direction.RIGHT);
            counter++;
        }
        Action[] arrayOfDirections = new Action[counter];
        for(int i=0;i < counter;i++)
        {
            arrayOfDirections[i]=helpArray[i];
        }

        return arrayOfDirections;
    }

    /*
     *creates a new state
     * @param currentAction - what tile to move in what direction
     * @return - the new state
     */
    public State result(Action currentAction){
        Tile tileToMove = currentAction.getCurrentTile(); // pointer of current tile
        int tilePlace = tileToMove.getCurrentPlace();
        Board newBoard = new Board(this.board); // in order not to change the current board
        State newState = new State(newBoard);// creating a new state to return so we don`t edit this one
        Tile copiedToMove = newBoard.getTilesBoard()[tilePlace];
        newBoard.moveTile(copiedToMove);// moving the tileToMove in the newBoard
        return newState;
    }

    /*
     *gets the current board of the current state
     * @return - the current board of the current state
     */
    public Board getBoard(){
        return this.board;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State state = (State) other;
        return board.equals(state.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}