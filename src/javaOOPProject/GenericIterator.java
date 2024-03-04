package javaOOPProject;

import java.util.ArrayList;
import java.util.Iterator;

public class GenericIterator<T> implements Iterator<T> {
    int index = 0;
    ArrayList<T> list;

    public GenericIterator(ArrayList<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() {
        return list.get(index);
    }

}
