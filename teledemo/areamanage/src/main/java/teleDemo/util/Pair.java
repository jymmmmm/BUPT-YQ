package teleDemo.util;

public class Pair <T,V>{
    T key;
    V value;
    Pair(T x_,V y_){
        this.key = x_;
        this.value = y_;
    }
    T getKey(){
        return this.key;
    }
    V getValue(){
        return this.value;
    }
}
