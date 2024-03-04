package javaOOPProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Repository<E> implements Serializable, Iterable<E> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public ArrayList<E> repo;

    public void setRepo(ArrayList<E> list) {
        repo = list;
    }

    public ArrayList<E> getRepo(){
        return repo;
    }

    public E getRepo(int i) {
        return repo.get(i);
    }

    public boolean isInRepo(E item) {
        return repo.contains(item);
    }

    public boolean addItemToRepo(E item) {
        if (!isInRepo(item)) {
            repo.add(item);
            return true;
        }
        return false;
    }

    public void rmItem(int index) {
        repo.remove(index);
    }

    public int length() {
        return repo.size();
    }

    public boolean equals(Repository<E> rep) {
        if (repo.equals(rep)) {
            return true;
        }
        return false;
    }

    public Iterator<E> iterator(){
        return new GenericIterator<E>(repo);

    }
}
