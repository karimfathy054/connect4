import java.util.ArrayList;
import java.util.Scanner;

public class Connect4MiniMax extends Minimax<State>{

    public Connect4MiniMax(int depth, State currentState, boolean isMaximizer){
        super(depth, currentState, isMaximizer);
    }

    @Override
    public boolean isTerminal(State state) {
        int cnt=0;
        int[] rows = {state.row1,state.row2,state.row3,state.row4,state.row5,state.row6};
        for(int i=0 ; i<6 ; i++){
            for(int j=1 ; j<=7 ; j++){
                if(state.getDigit(rows[i],j) == 0) continue;
                cnt++;
            }
        }
        if(cnt==42) return true;
        return false;
    }
    private int CenterDisc(State state,int player,int position){
        int[] rows = {state.row1,state.row2,state.row3,state.row4,state.row5,state.row6};
        int score=0;
        for(int i=0 ; i<6 ; i++){
            if(state.getDigit(rows[i],position)==player)score++;
        }
        return score;
    }

    private int VerticalStack(State state,int player){
        int[] rows = {state.row1,state.row2,state.row3,state.row4,state.row5,state.row6};
        int score=0;
        for(int i=1 ; i<=7 ; i++){
            for(int j=0 ; j<=4 ; j++){
                int l=j,k=0,cnt=0;
                while(k<2){
                    if(state.getDigit(rows[l],i)==player)cnt++;
                    l++;
                    k++;
                }
                if(cnt==2)score++;
            }
        }
        return score;
    }

    private int EmptySpaces(State state,int player){
        int score=0;
        int[] rows = {state.row1,state.row2,state.row3,state.row4,state.row5,state.row6};
        for(int i=0 ; i<6 ; i++){
            for(int j=1 ; j<=7 ; j++){
                if(state.getDigit(rows[i],j)==player) {
                    if (j < 7 && state.getDigit(rows[i], j + 1) == 0) score++;
                    if (j > 1 && state.getDigit(rows[i], j - 1) == 0) score++;
                    if (i < 5 && state.getDigit(rows[i+1], j) == 0) score++;
                    if (i > 0 && state.getDigit(rows[i-1], j) == 0) score++;
                    if (j < 7 && i < 5 && state.getDigit(rows[i + 1], j + 1) == 0) score++;
                    if (j > 1 && i > 0 && state.getDigit(rows[i - 1], j - 1) == 0) score++;
                    if (j > 1 && i < 5 && state.getDigit(rows[i + 1], j - 1) == 0) score++;
                    if (j < 7 && i > 0 && state.getDigit(rows[i - 1], j + 1) == 0) score++;
                }
            }
        }
        return score;
    }
    @Override
    public int estimate(State state) {
        int heuristic = 0;
        if(isTerminal(state)){
            if(state.getScore(4,2)>state.getScore(4,1))return 10000;
            else if(state.getScore(4,2)<state.getScore(4,1))return -10000;
            return 0;
        }
        //score , 3-->4 , 2-->4
        // Number of Player's Fours (Potential Wins): +1000 points for each possible winning move.
        heuristic += (state.getScore(4,2)*1000);

        // Number of Opponent's Fours (Blocking): -1000 points for each potential winning move by the opponent.
        heuristic -= (state.getScore(4,1)*1000);

        // Number of Player's Threes: +100 points for each three-in-a-row.
        // Open Two-Way Threes: +200 points for each open-ended three-in-a-row.
        heuristic += (state.getScore(3,2)*500);

        // Number of Opponent's Threes: -100 points for each opponent's three-in-a-row.
        // Opponent's Open Two-Way Threes: -200 points for each open-ended three-in-a-row.
        heuristic -= (state.getScore(3,1)*500);


        // 0 1 1 1 0 0 --> 200
        // 0 1 1 1 2 0 --> 100
        // Number of Player's Threes: +100 points for each three-in-a-row.
        // Open Two-Way Threes: +100 points for each open-ended three-in-a-row.
        heuristic += (state.getScore(2,2)*100);

        // Number of Player's Threes: -100 points for each three-in-a-row.
        // Open Two-Way Threes: -100 points for each open-ended three-in-a-row.
        heuristic -= (state.getScore(2,1)*100);

        // Center Control: +50 points for each disc in the center column.
        heuristic += (CenterDisc(state,2,4)*50);

        // Opponent's Center Control: -50 points for each disc in the center column.
        heuristic -= (CenterDisc(state,1,4)*50);

        // Center Control: +40 points for each disc in 3 , 5 column.
        heuristic += (CenterDisc(state,2,5)*40);
        heuristic += (CenterDisc(state,2,3)*40);

        // Opponent's Center Control: -40 points for each disc in 3 , 5 column.
        heuristic -= (CenterDisc(state,1,5)*40);
        heuristic -= (CenterDisc(state,1,3)*40);

        // Edge Control: +30 points for each disc in the outer columns.
        heuristic += (CenterDisc(state,2,1)*30);
        heuristic += (CenterDisc(state,2,7)*30);

        // Opponent's Edge Control: -30 points for each disc in the outer columns.
        heuristic -= (CenterDisc(state,1,1)*30);
        heuristic -= (CenterDisc(state,1,7)*30);

        // Blocked Two-Way Threes: -50 points for each blocked two-way three. (Dropped)
        // 0 2 1 1 1 2 0 --> -50 +100 = +50 / 0
        // 0 0 2 2 2 0 0

        // Vertical Stacks: +20 points for each consecutive pair of discs in a column.
        heuristic += (VerticalStack(state,2)*20);

        // Opponent's Vertical Stacks: -20 points for each consecutive pair of discs in a column.
        heuristic -= (VerticalStack(state,1)*20);

        // Empty Spaces Near Existing Discs: +10 points for each empty space adjacent to a player's disc.
        // 0 0 0 0 0 0 0
        // 0 0 0 2 0 0 0
        // 0 0 1 1 1 0 0
        heuristic += (EmptySpaces(state,2)*10);

        // Empty Spaces Near Existing Discs: +10 points for each empty space adjacent to a player's disc.
        heuristic -= (EmptySpaces(state,1)*10);


        return heuristic;
    }

    @Override
    public ArrayList<State> nextStates(State state) {
        return state.getNextStates();
    }

    @Override
    public void Play(){

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        State state = new State();
        while (true) {
            state.printGrid();
            Connect4MiniMax game ;
            if(state.player==2) {
                game = new Connect4MiniMax(7, state, true);
                System.out.println(game.minimaxWithPruning());
                state = game.parentMap.get(state);
            }
            else {
                game = new Connect4MiniMax(7, state, false);
                System.out.println(game.minimaxWithPruning());
                state = game.parentMap.get(state);
            }
            if(game.isTerminal(state)){
                break;
            }
        }
    }
}
