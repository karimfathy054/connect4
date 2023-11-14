import java.util.HashSet;
import java.util.Random;

public class Connect4Hasher extends Hasher<State>{
    public Connect4Hasher(){
        super();
        //initializer
        this.hash = new long[42][3];
        HashSet<Long> usedValues = new HashSet<>();
        Random rand = new Random();

        for(int i = 0; i < 42; i++){
            for(int j = 0; j < 3; j++){
                long val = rand.nextLong();
                if(!usedValues.contains(val)){
                    this.hash[i][j] = val;
                    usedValues.add(val);
                }
            }
        }
    }
    @Override
    public long hashGrid(State state) {
        long h = 0;
        int[] rows = new int[]{
                state.row1,
                state.row2,
                state.row3,
                state.row4,
                state.row5,
                state.row6
        };
        //for each row
        for(int i = 0; i < 6; i++){
            //for each slot in row
            for(int j = 1; j <= 7; j++){
                int valueInSlot = state.getDigit(rows[i], j);
                h ^= this.hash[6*(i)+(j-1)][valueInSlot];
            }
        }
        return h;
    }
}
