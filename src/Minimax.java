import java.util.ArrayList;

public abstract class Minimax<T> {

    int depth;
    T currentState;
    boolean isMaximizer;

    public Minimax(int depth, T currentState, boolean isMaximizer){
        this.depth = depth;
        this.currentState = currentState;
        this.isMaximizer = isMaximizer;
    }

    public abstract boolean isTerminal(T state);
    public abstract int estimate(T state);

    /*TODO in case of maximizer return the states sorted highest to lowest
      TODO and in case of minimizer return the states sorted lowest to highest
      TODO to prune more values earlier.
      TODO [IF PRACTICAL]
    */
    public abstract ArrayList<T> nextStates(T state);

    //TODO print tree in console/GUI
    //TODO return the optimal action or the next optimal state
    private int minimaxHelper(T state, boolean isMaximizer, int depth){
        if(depth == 0 || isTerminal(state)){
            return estimate(state);
        }

        if(isMaximizer){
            int maxValue = Integer.MIN_VALUE;
            for(T nextState: nextStates(state)){
                maxValue = Math.max(maxValue, minimaxHelper(nextState, false, depth-1));
            }
            return maxValue;
        }else{
            int minValue = Integer.MAX_VALUE;
            for(T nextState: nextStates(state)){
                minValue = Math.min(minValue, minimaxHelper(nextState, true, depth-1));
            }
            return minValue;
        }
    }

    public int minimax(){
        return minimaxHelper(currentState, this.isMaximizer, this.depth);
    }

    private int pruningHelper(T state, boolean isMaximizer, int depth, int alpha, int beta){
        //alpha = value assigned to maximizer,
        //beta = value assigned to minimizer

        if(depth == 0 || isTerminal(state)){
            return estimate(state);
        }
        if(isMaximizer){

            int maxVal = Integer.MIN_VALUE;

            for(T nextState: nextStates(state)){
                int val = pruningHelper(nextState, false, depth-1, alpha, beta);
                maxVal = Math.max(maxVal, val);
                alpha = Math.max(alpha, val);

                if(alpha >= beta){break;}
            }
            return maxVal;
        }else{
            int minVal = Integer.MAX_VALUE;
            for(T nextState: nextStates(state)){
                int val = pruningHelper(nextState, true, depth-1, alpha, beta);
                minVal = Math.min(minVal, val);
                beta = Math.min(beta, val);
                if(beta <= alpha){break;}
            }
            return minVal;
        }
    }

    public int minimaxWithPruning(){
        return pruningHelper(
                this.currentState,
                this.isMaximizer,
                this.depth,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE
        );
    }

}

