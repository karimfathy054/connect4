public class Main {
    static int x=10;
    static int posInCol(int position){
        int bitMask = 3;
//        for (int i = 0; i < position; i++) {
//            bitMask= bitMask<<2;
//        }
//        int result = x&bitMask;
//        for (int i = 0; i < position; i++) {
//            result = result>>2;
//        }
        int z = x;
        for (int i = 0; i < position; i++) {
            z= z%10;
            z/=10;
        }
        return z;
    }

    public static void main(String[] args) {
        System.out.println(posInCol(1));
    }
}
