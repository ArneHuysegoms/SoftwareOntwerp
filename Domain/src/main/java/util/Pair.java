package util;

public class Pair<T> {

    private T first;
    private T second;

    public Pair(T first, T second){
        this.setFirst(first);
        this.setSecond(second);
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Pair){
            Pair p = (Pair) o;
            return p.getFirst().equals(this.getFirst()) && p.getSecond().equals(this.getSecond());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return (this.getFirst().hashCode() + this.getSecond().hashCode()) %37;
    }

    @Override
    public String toString(){
        return "First: " + this.getFirst().toString() + " Second: " + this.getSecond().toString();
    }
}
