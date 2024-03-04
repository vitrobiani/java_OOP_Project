package javaOOPProject;

import java.io.Serializable;

public abstract class Query implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected String question;
    public static int numOfQuerries;
    protected QueryRepository QR;
    public int ordNum;

    public enum difficulty {
        easy, medium, hard
    };

    protected difficulty diff;

    public Query(String q, difficulty diff, QueryRepository QR) {
        question = q;
        this.diff = diff;
        this.QR = QR;
        ordNum = ++numOfQuerries;
    }

    public void updateQuestion(String nQ) {
        question = nQ;
    }

    public String getQues() {
        return question;
    }

    public QueryRepository getQR() {
        return QR;
    }

    public void setQR(QueryRepository qR) {
        QR = qR;
    }

    public int getNumOfQuerries() {
        return numOfQuerries;
    }

    public void setNumOfQueries(int o) {
        numOfQuerries = o;
    }

    public difficulty getDiff() {
        return diff;
    }

    public int getOrdNum() {
        return ordNum;
    }

    public abstract String toStringForTestRes();

    public abstract String toStringForTest();

    public String toString() {
        return  question + "   difficulty: " + diff + '\n';
    }

    public boolean equals(Query q) {
        return (q.diff == diff && q.getQues() == question);
    }

}
