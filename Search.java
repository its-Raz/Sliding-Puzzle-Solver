import java.util.*;

public class Search {
    public static final int SOLVED = 0;
    public static final int UNSOLVABLE = 1;
    public static final int OUT_OF_MEMORY = 2;
    public static final int UNSOLVED = 3;

    private int expandedNodes;
    private List<Action> result;
    private int status = UNSOLVED;

    /**
     * Constructs the root node of the game based on an initial board.
     *
     * @param boardString String representing the initial board
     * @return The root node used to search for a solution
     */
    /*
     * This method get the string that represent the board and generate the starting node of the game
     * @param boardString - the string that represent the board
     * @return - the starting node of the game
     * */
    private Node getRoot(String boardString) {
        int[][] data = Help.convertToNumbers(boardString);
        Board board = new Board(data);
        State state = new State(board);
        return new Node(state);
    }

    /**
     * Performs a Greedy Best First Search, using node heuristic function.
     *
     * @param boardString String representing the initial board
     * @return List of actions to reach the goal state
     */
    public List<Action> search(String boardString) {
        try {
            Node root = getRoot(boardString);

            Queue<Node> frontier = new PriorityQueue<>(Comparator.comparing(Node::heuristicValue));  // Stores future nodes
            Set<State> visited = new HashSet<>();  // Used for duplicate detection
            frontier.add(root);

            while (!frontier.isEmpty()) {
                Node node = frontier.remove();  // Get node with smallest heuristic value
                if (node.getState().isGoal()) {
                    result = extractSolution(node);  // Extracting the solution
                    status = SOLVED;
                    return result;
                }
                expandedNodes++;
                Node[] children = node.expand();

                for (Node child : children) {  // Iterate over all possible child nodes
                    if (!visited.contains(child.getState())) {  // Check for duplication
                        visited.add(child.getState());
                        frontier.add(child);
                    }
                }
            }
            status = UNSOLVABLE;  // Unsolvable game
            return null;
        } catch (OutOfMemoryError err) {  // Out of memory - probably due to an explosion of the frontier
            status = OUT_OF_MEMORY;
            return null;
        }
    }

    /**
     * Extracts a solution from a given node by iterating backward from the node up to the root.
     * The given node satisfies node.getState().isGoal() == true.
     *
     * @param node Node with the goal state
     * @return List of actions to reach the goal state
     */
    private List<Action> extractSolution(Node node) {
        List<Action> actions = new ArrayList<>();
        while (node != null) {  // Iterate backwards until reaching the root
            actions.add(node.getAction());
            node = node.getParent();
        }
        Collections.reverse(actions);  // Reverse the list
        actions.remove(0);
        return actions;
    }

    public int getStatus() {
        return status;
    }

    public List<Action> getResult() {
        return result;
    }

    public int getExpandedNodes() {
        return expandedNodes;
    }
}
