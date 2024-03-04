package javaOOPProject;

import java.io.Serializable;

public class Subject implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public AnswerRepository AR;
    public QueryRepository QR;
    private String name;

    public Subject(String name) {
        this.name = name;
        this.AR = new AnswerRepository();
        this.QR = new QueryRepository();
    }

    public AnswerRepository getAR() {
        return AR;
    }

    public void setAR(AnswerRepository aR) {
        AR = aR;
    }

    public QueryRepository getQR() {
        return QR;
    }

    public void setQR(QueryRepository qR) {
        QR = qR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "javaOOPProjrct.Subject [name=" + name + "]\n" + QR.toString() + '\n' + AR.toString();
    }

    public boolean equals(Subject s) {
        return s.getName().equals(name);
    }

}
