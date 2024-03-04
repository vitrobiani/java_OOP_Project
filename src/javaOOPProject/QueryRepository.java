package javaOOPProject;

import java.io.Serializable;
import java.util.ArrayList;

public class QueryRepository extends Repository<Query> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public QueryRepository(ArrayList<Query> q) {
        repo = q;
    }

    public QueryRepository() {
        repo = new ArrayList<>();
    }

    public ArrayList<openQuery> getOpenQueryArray() {
        ArrayList<openQuery> openQueryArray = new ArrayList<openQuery>();
        for (int i = 0; i < length(); i++) {
            if (repo.get(i).getClass().equals(openQuery.class)) {
                openQueryArray.add((openQuery) repo.get(i));
            }
        }
        return openQueryArray;
    }

    public ArrayList<closedQuery> getClosedQueryArray() {
        ArrayList<closedQuery> closedQueryArray = new ArrayList<>();
        for (int i = 0; i < length(); i++) {
            if (repo.get(i).getClass().equals(closedQuery.class)) {
                closedQueryArray.add((closedQuery) repo.get(i));
            }
        }
        return closedQueryArray;
    }

    public String closedQuerytoString() {
        StringBuffer sB = new StringBuffer();
        for (int i = 0; i < getClosedQueryArray().size(); i++) {
            sB.append(getClosedQueryArray().get(i).getOrdNum() + ")" + getClosedQueryArray().get(i).toString() + '\n'
                    + '\n');
        }
        return sB.toString();
    }

    public String openQuerytoString() {
        StringBuffer sB = new StringBuffer();
        for (int i = 0; i < getOpenQueryArray().size(); i++) {
            sB.append(
                    getOpenQueryArray().get(i).getOrdNum() + ")" + getOpenQueryArray().get(i).toString() + '\n' + '\n');
        }
        return sB.toString();
    }

    public String toString() {
        StringBuffer sB = new StringBuffer();
        sB.append("\nthe Queries:  \n");

        int i = 1;
        for (Query q : repo) {
            sB.append(i + ".  " + q.toString() + '\n' + '\n');
            i++;
        }
        return sB.toString();
    }

}
