package logic.entity;


public interface Storable {
    int get();

    void set(int value);

    int pull();

    void push(int quantity);

}
