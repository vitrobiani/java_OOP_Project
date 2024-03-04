package javaOOPProject;

import java.util.ArrayList;

public class Services {
    public SubjectRepository SR;

    public Services(SubjectRepository SR) {
        this.SR = SR;
    }

    public void setSR(SubjectRepository sr) {
        SR = sr;
    }

    public boolean addQuery(Query q, int subIndex) {
        return SR.getRepo(subIndex).getQR().addItemToRepo(q);
    }

    public void rmQuery(int subIndex, int i) {
        SR.getRepo(subIndex).getQR().rmItem(i);
    }

    public ArrayList<Query> getAllQueryArray(int i) {
        return SR.getRepo(i).getQR().getRepo();
    }

    public QueryRepository getQR(int SubIndex) {
        return SR.getRepo(SubIndex).getQR();
    }

    public AnswerRepository getAR(int SubIndex) {
        return SR.getRepo(SubIndex).getAR();
    }

    public Query getQuery(int subIndex, int queryIndex) {
        return SR.getRepo(subIndex).getQR().getRepo(queryIndex);
    }

    public String QRtoString(int SubIndex) {
        return SR.getRepo(SubIndex).getQR().toString();
    }

    public String ARtoString(int SubIndex) {
        return SR.getRepo(SubIndex).getAR().toString();
    }

    public boolean addAnswer(int SubIndex, String ans) {
        return SR.getRepo(SubIndex).getAR().addItemToRepo(ans);
    }

    public boolean addQuery(int SubIndex, Query q) {
        return SR.getRepo(SubIndex).getQR().addItemToRepo(q);
    }

    public int getARlength(int SubIndex) {
        return SR.getRepo(SubIndex).getAR().length();
    }

    public int getQRlength(int SubIndex) {
        return SR.getRepo(SubIndex).getQR().length();
    }
}
