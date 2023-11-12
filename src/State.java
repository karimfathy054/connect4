import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<State> nextStates = new ArrayList<State>();
        for (int i = 0; i < 7; i++) {
            State successor = (State) this.clone();
            successor.playturn(i+1);
            nextStates.add(successor);
        }
        return nextStates;
    }

    public int getScore(int n){
        int[] rows = {row1,row2,row3,row4,row5,row6};
        int score=0;
        for(int i=0 ; i<6 ; i++){
            for(int j=1 ; j<=6 ; j++){
                int cnt=0,l=j,k=n;
                while(k>0 && l<=7){
                    if(getDigit(rows[i],l)==player){
                        cnt++;
                    }
                    k--;
                    l++;
                }
                if(cnt==n)
                    score++;
            }
        }
        for(int i=1 ; i<=7 ; i++){
            for(int j=0 ; j<=4 ; j++){
                int cnt=0,l=j,k=n;
                while(k>0 && l<=5){
                    if(getDigit(rows[l],i)==player)cnt++;
                    k--;
                    l++;
                }
                if(cnt==n) score++;
            }
        }

        for(int i=5 ; i>=1 ; i--){
            for(int j=1 ; j<=6 ; j++){
                int cnt=0 , l=i , r=j,k=n;
                while(k>0 && l>=0 && r<=7){
                    if(getDigit(rows[l],r)==player){
                        cnt++;
                    }
                    l--;
                    r++;
                    k--;
                }
                if(cnt==n)
                    score++;
            }
        }

        for(int i=5 ; i>=1 ; i--){
            for(int j=7 ; j>=2 ; j--){
                int cnt=0,l=i,r=j,k=n;
                while(k>0 && l>=0 && r>=1){
                    if(getDigit(rows[l],r)==player){
                        cnt++;
                    }
                    l--;
                    r--;
                    k--;
                }
                if(cnt==n)
                    score++;
            }
        }

        return score;
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





    public static void main(String[] args) throws CloneNotSupportedException {
        State s = new State();
        System.out.println(s);
        s.playturn(5); // 1
        s.playturn(5); // 2
        s.playturn(4); // 1
        s.playturn(5); // 2
        s.playturn(3); // 1
        s.playturn(5); // 2
        s.playturn(2); // 1
        s.playturn(5); // 2
        s.playturn(2); // 1
        System.out.println(s);
        System.out.println(s.getScore(4));
        ArrayList<State> neigh = s.getNextStates();
        for(State x : neigh){
            System.out.println(x);
            System.out.println(x.getScore(4));
        }
    }
}
