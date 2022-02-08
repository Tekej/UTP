package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    T x ;

    public Maybe(T x) {
        this.x=x;
    }
    public Maybe(){
    }
    public static <T>Maybe<T> of(T x) {
        return new Maybe<>(x);
    }
    public void ifPresent(Consumer<T> cons){
        if(isPresent())cons.accept(x);
    }
    public <T>Maybe<T> map(Function<T,T> func){
        if(isPresent()){
            return new Maybe<>(func.apply((T)x));
        }else{
            return new Maybe<>();
        }
    }
    public T get(){
        if(!isPresent()){
            throw new NoSuchElementException("maybe is empty");
        }
        return x;
    }

    public <T> T orElse(T defVal) {
        if(isPresent()){
            return (T)x;
        }else{
            return defVal;
        }
    }
    public Maybe<T> filter(Predicate<T> pred){
        if(isPresent()){
            pred.test(x);
            return this;
        }
        return null;
    }

    boolean isPresent(){
        if(x!=null){
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        if(isPresent()){
            return "Maybe has value "+x;
        }
        return "Maybe is empty";
    }
}
