import java.util.ArrayList;

public class State implements Cloneable {

    int row1;
    int row2;
    int row3;
    int row4;
    int row5;
    int row6;

    int player = 1;//1 for human ,2 for AI

    public State() {
        this.row1 = 0;
        this.row2 = 0;
        this.row3 = 0;
        this.row4 = 0;
        this.row5 = 0;
        this.row6 = 0;
        this.player = 1;
    }


    boolean playturn(int col){

        if(getDigit(row6,col)!=0) return  false;
        if(getDigit(row1,col) ==0){
            row1 = setDigit(row1,col,player);
            player=player == 1?2:1;
            return true;
        }
        if(getDigit(row2,col) ==0){
            row2 = setDigit(row2,col,player);
            player=player == 1?2:1;
            return true;
        }if(getDigit(row3,col) ==0){
            row3 = setDigit(row3,col,player);
            player=player == 1?2:1;
            return true;
        }if(getDigit(row4,col) ==0){
            row4 = setDigit(row4,col,player);
            player=player == 1?2:1;
            return true;
        }if(getDigit(row5,col) ==0){
            row5 = setDigit(row5,col,player);
            player=player == 1?2:1;
            return true;
        }if(getDigit(row6,col) ==0){
            row6 = setDigit(row6,col,player);
            player=player == 1?2:1;
            return true;
        }
        return true;
    }

    public static int getDigit(int number, int position) {
        // Ensure position is non-negative
        position = Math.max(0, position);

        // Extract the digit using modulo and integer division
        int digit = (number / (int) Math.pow(10, 7-position)) % 10;

        return digit;
    }
    public static int setDigit(int number, int position,int value) {
        // Ensure position is non-negative
        position = Math.max(0, position);

        value = value * (int) Math.pow(10, 7-position);
        // Extract the digit using modulo and integer division
        number = number+value;

        return number;
    }

    public ArrayList<State> getNextStates() throws CloneNotSupportedException {
        int[] rows = {row1,row2,row3,row4,row5,row6};
        ArrayList<State> nextStates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if(getDigit(rows[5],i) !=0) continue;
            int last=5;
            for (int j = 0; j < rows.length; j++) {
                if(getDigit(rows[j],i)==0) continue;
                last = j-1;
                break;
            }
            State successor = (State) this.clone();

            successor.row1=setDigit(successor.row1,i,player);

            nextStates.add(successor);
        }
        return nextStates;
    }



    @Override
    public String toString() {
        return "State{\n" +
                ", row6=" + row6 +"\n"+
                ", row5=" + row5 +"\n"+
                ", row4=" + row4 +"\n"+
                ", row3=" + row3 +"\n"+
                ", row2=" + row2 +"\n"+
                ", row1=" + row1 +"\n"+
                ", player=" + player +
                '}';
    }





    public static void main(String[] args) {
        State s = new State();
        System.out.println(s);
        s.playturn(5);
        s.playturn(5);
        System.out.println(s);
        System.out.println(getDigit(s.row1,5));
    }
}
