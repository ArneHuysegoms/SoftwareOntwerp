package figures.helperClasses;

public class Pair<A,B> {
    private final A first;
    private final B second;

    public Pair(A first, B second){
        this.first = first;
        this.second = second;
    }

    public A getA(){
        return first;
    }

    public B getB(){
        return second;
    }

}
