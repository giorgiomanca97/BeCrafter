package logic.entity.interfaces;


public interface Storable {
    int get();

    void set(int quantity);

    void add(int quantity);

    void remove(int quantity);

    void pull(Storable to, int quantity);

    void push(Storable from, int quantity);

}
