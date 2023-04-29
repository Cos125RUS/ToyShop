package org.example.Model;

public class Gift extends Toy{
    public Gift(int id, String name) {
        super(name);
        super.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
