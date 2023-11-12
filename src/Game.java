import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    static class State implements Cloneable{
        char[][] board ;//r y -
        Boolean human; //true=human turn to play,false = AI turn to play

        public State() {
            this.board = new char[6][7];
        }

        void initialState(){
            this.board = new char[6][7];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    board[i][j] = '-';
                }
            }
            this.human = true;
        }

        @Override
        public State clone() {
                State clone = new State();
                for (int i = 0; i < board.length; i++) {
                    clone.board[i] = this.board[i].clone();
                }
                clone.human =this.human;
                return clone;
        }

        public boolean isTerminal(){
            char piece = human? 'y':'r';
            //check horizontally
            String winning = ""+piece+""+piece+""+piece+""+piece;
            for (int i = 0; i <board.length ; i++) {
                if(String .valueOf(board[i]).contains(winning))
                    return true;//means to lose if human== true 
            }
            //check vertically
            for (int i = 0; i < board[0].length; i++) {
                String col ="";
                for (char[] chars : board) {
                    col += chars[i];
                }
                if(col.contains(winning)) return true;//means to lose if human== true 
            }
            //check diagonals ??
            return false;
        }

        public ArrayList<State> getNextStates(){
            char piece = this.human? 'r':'y';
            ArrayList<State> nextStates = new ArrayList<State>();
            for (int i = 0; i < board[0].length; i++) {
                if(board[0][i] !='-') continue;
                int last=5;
                for (int j = 0; j < board.length; j++) {
                    if(board[j][i]=='-') continue;
                    last = j-1;
                    break;

                }
                State successor = this.clone();
                successor.board[last][i] = piece;
                successor.human = !successor.human;
                nextStates.add(successor);
            }
            return nextStates;
        }

        void printState(){
            for (char[] row :
                    board) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println("human turn="+human);
        }
        boolean playTurn(int col){
            if(board[0][col]!='-') return false;
            char piece = human? 'r':'y';
            int last=5;
            for (int j = 0; j < board.length; j++) {
                if(board[j][col]=='-') continue;
                last = j-1;
                break;
            }
            board[last][col]=piece;
            human = !human;
            return true;
        }
    }

    public static void main(String[] args) {
        State s= new State();
        s.initialState();
        s.printState();
        s.playTurn(3);
        s.printState();

        ArrayList<State> ss = s.getNextStates();
        for (State x :
                ss) {
            x.printState();
        }

    }

}
