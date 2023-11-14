import java.util.HashSet;
import java.util.Random;

public abstract class Hasher<T> {

    long[][] hash;
    public Hasher(){}

    public abstract long hashGrid(T state);
}
