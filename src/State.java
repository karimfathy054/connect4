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
        this.player = 2;
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

    public int getDigit(int number, int position) {
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

    public ArrayList<State> getNextStates() {
        int[] rows = {row1,row2,row3,row4,row5,row6};
        ArrayList<State> nextStates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            State successor = null;
            try {
                successor = (State) this.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            if(successor.playturn(i+1)) {
                nextStates.add(successor);
            }
        }
        return nextStates;
    }

    private int scoreHelper(int[] arr,int n,int player){
        // [ 1 , 1 , 1 , 1] --> 1
        // cnt = 4
        // open = 0

        // [ 1 , 0 , 1 , 1 ]
        // cnt = 3
        // open = 1

        // [ 0 , 1 , 0 , 1 ]
        // cnt = 2
        // open = 2
        int cnt = 0, open = 0;
        for(int i=0 ; i<4 ; i++){
            if(arr[i]==player)cnt++;
            else if(arr[i]==0)open++;
        }
        if((open == 4-n) && (cnt == n))return 1;
        return 0;
    }
    public int getScore(int n,int player){
        int[] rows = {row1,row2,row3,row4,row5,row6};
        int score=0;
        for(int i=0 ; i<6 ; i++){
            for(int j=1 ; j<=4 ; j++){
                int l=j,k=0;
                int[] arr = new int[4];
                while(k<4){
                    arr[k++] = getDigit(rows[i],l);
                    l++;
                }
                score += scoreHelper(arr,n,player);
            }
        }

        for(int i=1 ; i<=7 ; i++){
            for(int j=0 ; j<=2 ; j++){
                int l=j,k=0;
                int[] arr =new int[4];
                while(k<4){
                    arr[k++] = getDigit(rows[l],i);
                    l++;
                }
                score += scoreHelper(arr,n,player);
            }
        }

        for(int i=5 ; i>=3 ; i--){
            for(int j=1 ; j<=4 ; j++){
                int  l=i , r=j,k=0;
                int[] arr =new int[4];
                while(k<4){
                    arr[k++] = getDigit(rows[l],r);
                    l--;
                    r++;
                }
                score += scoreHelper(arr,n,player);
            }
        }

        for(int i=5 ; i>=3 ; i--){
            for(int j=7 ; j>=4 ; j--){
                int l=i,r=j,k=0;
                int[] arr=new int[4];
                while(k<4){
                    arr[k++] = getDigit(rows[l],r);
                    l--;
                    r--;
                }
                score += scoreHelper(arr,n,player);
            }
        }

        return score;
    }

    public void printGrid() {
        int[] rows = {row1,row2,row3,row4,row5,row6};
        for(int i=5 ; i>=0 ; i--){
            for(int j=1 ; j<=7 ; j++){
                System.out.print(getDigit(rows[i],j) +" ");
            }
            System.out.println("\n");
        }
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
        System.out.println(s.getScore(4,1));
        System.out.println(s);
        ArrayList<State> neigh = s.getNextStates();
        for(State x : neigh){
            System.out.println(x);
        }
    }
}
