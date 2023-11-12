import java.util.ArrayList;

//JUST FOR TESTING MINIMAX ALGORITHM AND PRUNING

public class TicMinimax extends Minimax<char[][]>{

    // x = maximizer, o = minimizer
    // x starts first

    public TicMinimax(int depth, char[][] currentState, boolean isMaximizer) {
        super(depth, currentState, isMaximizer);
    }

    @Override
    public boolean isTerminal(char[][] state) {
        return state[0][0] != '-' && state[0][1] != '-' && state[0][2] != '-' &&
                state[1][0] != '-' && state[1][1] != '-' && state[1][2] != '-' &&
                state[2][0] != '-' && state[2][1] != '-' && state[2][2] != '-';
    }

    private boolean checkRows(char[][] state, char player){
        return state[0][0] == state[0][1] && state[0][1] == state[0][2] && state[0][2] == player ||
                state[1][0] == state[1][1] && state[1][1] == state[1][2] && state[1][2] == player ||
                state[2][0] == state[2][1] && state[2][1] == state[2][2] && state[2][2] == player;
    }

    private boolean checkColumns(char[][] state, char player){
        return state[0][0] == state[1][0] && state[1][0] == state[2][0] && state[2][0] == player ||
                state[0][1] == state[1][1] && state[1][1] == state[2][1] && state[2][1] == player ||
                state[0][2] == state[1][2] && state[1][2] == state[2][2] && state[2][2] == player;
    }

    private boolean checkDiagonals(char[][] state, char player){
        return state[0][0] == state[1][1] && state[1][1] == state[2][2] && state[2][2] == player ||
                state[0][2] == state[1][1] && state[1][1] == state[2][0] && state[2][0] == player;
    }
    @Override
    public int estimate(char[][] state) {
        if(checkRows(state, 'o') ||
                checkColumns(state, 'o') ||
                checkDiagonals(state, 'o')){return -1;}
        if(checkRows(state, 'x') ||
                checkColumns(state, 'x') ||
                checkDiagonals(state, 'x')){return 1;}
        return 0;
    }

    private char getNextPlayer(char[][] state){
        int x = 0, o = 0;
        for(int i = 0 ; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(state[i][j] == 'x'){x++;}
                else if(state[i][j] == 'o'){o++;}
            }
        }
        if(x == o) return 'x';
        return 'o';
    }

    private char[][] copyState(char[][] state){
        char[][] copy = new char[3][3];
        for(int i = 0; i < 3; i++){
            System.arraycopy(state[i], 0, copy[i], 0, 3);
        }
        return copy;
    }

    @Override
    public ArrayList<char[][]> nextStates(char[][] state) {
        ArrayList<char[][]> arr = new ArrayList<>();
        char nextPlayer = getNextPlayer(state);
        for(int i = 0 ; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(state[i][j] == '-'){
                    char[][] nextState = copyState(state);
                    nextState[i][j] = nextPlayer;
                    arr.add(nextState);
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        TicMinimax t = new TicMinimax(
                10,
                new char[][]{
                        new char[]{'x', '-', '-'},
                        new char[]{'-', 'x', 'o'},
                        new char[]{'x', '-', 'o'}
                },
                true
        );
        System.out.println(t.minimax());
        System.out.println(t.minimaxWithPruning());
    }
}
