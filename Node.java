public class Node {
    private State state;
    private Node parent;
    private Action previousAction;

    /** FINAL VARIABLES DECLARATION **/
    private final static int ZERO = 0;
    private final static int ONE = 1;


    /** CONSTRUCTORS  **/
    /*
     * The constructor generate a new Node that is not the starting one.
     * @param state - the state that the node contains
     * @param parent - the node parent of this node
     * @param previousAction - the action that performed to get to this node.
     * */
    public Node(State state, Node parent, Action previousAction)
    {
        this.state = state;
        this.parent = parent;
        this.previousAction = previousAction;
    }
    /*
     * The constructor generate the starting node
     * @param startingState - the starting state that initialize from get root method
     * */
    public Node(State startingState)
    {
        this(startingState,null,null);
    }


    /** METHODS  **/
    /*
     * this method checks what are the possible action to perform on the current state and generate all the possible nodes from the current one
     * @return an array of nodes
     *
     * */
    public Node[] expand() {
        Action[] actions = filterPreviousAction(state.actions());//all the possible actions
        Node[] expandedNodes = new Node[actions.length];
        for(int k=ZERO;k< actions.length;k++){
            State newState = state.result(actions[k]);
            expandedNodes[k] = new Node(newState, this, actions[k]);
        }
        return expandedNodes;
    }

    /*
     *calculates a heuristic value for each node
     *@return the heuristic value for each node
     * */
    public int heuristicValue()
    {
        Board board = state.getBoard();
        Tile[] tileBoard = board.getTilesBoard();
        int sum = ZERO;
        for(int i =ZERO; i < tileBoard.length;i++) {
            int currentR = Help.convertToMatrixSpot(i, board.getBoardWidth())[ZERO];
            int currentC = Help.convertToMatrixSpot(i, board.getBoardWidth())[ONE];
            sum += tileBoard[i].getDistance(currentR, currentC);
            if ((i + board.getBoardHeight()) < board.getBoardWidth()) {
                //if neighbors are in the same column
                if(tileBoard[i].getCurrentPlace() + board.getBoardHeight() == tileBoard[i].getWantedPlace()) {
                    if (tileBoard[i + board.getBoardHeight()].getValue() + board.getBoardHeight() == tileBoard[i].getValue()){
                        sum += 2;
                    }
                }
            }
            if(i>0&&tileBoard[i].getValue()!=0 &&tileBoard[i].getValue()<tileBoard[i-1].getValue())
            {//numbers of inversions
                sum++;
                if(i>1&&tileBoard[i].getValue()<tileBoard[i-2].getValue() )
                {
                    sum++;
                }
            }
        }
        return sum;
    }
    /** GETTERS  **/
    /*
     * the method return the current state in the node
     *@return the state object in the node
     * */
    public State getState()
    {
        return this.state;
    }
    /*
     * the method return the parent node of this node
     *@return the node object of the parent
     * */
    public Node getParent()
    {
        return parent;
    }
    /*
     * the method return the action that was performed in order to generate the state of the current node
     *@return an Action object of the previous action
     * */
    public Action getAction() { return previousAction;}

    /** END OF GETTERS  **/
    /*
     * the method remove the previous action from the actions that are possible to perform on the current state, in order to avoid expanding a redundant node
     *@param actions - array of all the possible actions that can be performed on the current state
     * @return - array of actions without the previous action
     * */
    private Action[] filterPreviousAction(Action[] actions)
    {

        if(this.previousAction!=null&&actions.length>ONE){
            Action contraAction = previousAction.convertPrevious();
            int counter=ZERO;
            Action[] newArray = new Action[actions.length-ONE];
            for (int k=ZERO;k< actions.length;k++)
            {
                if( !(actions[k].equals(contraAction)))
                {
                    if(counter>newArray.length-ONE)
                    {System.out.println("Counter is bigger");}
                    else {
                        newArray[counter]=actions[k];
                        counter++;
                    }
                }
            }
            return newArray;
        }
        return actions;
    }

}