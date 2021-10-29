package ro.ubbcluj.map.Utils;

import java.util.Objects;

public class MyPair<T> {

    public T first;
    public T second;
    public MyPair(T first, T second){
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPair<?> myPair = (MyPair<?>) o;
        return Objects.equals(first, myPair.first) && Objects.equals(second, myPair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
