package javaOOPProject;

public class openQuery extends Query {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private AnswerAdapter schoolAnswerAdapter;

    public openQuery(String q, Query.difficulty diff, QueryRepository QR, AnswerAdapter schoolAnswerAdapter) {
        super(q, diff, QR);
        this.schoolAnswerAdapter = schoolAnswerAdapter;
        schoolAnswerAdapter.setIsCorrect(true); // setting isCorrect in javaOOPProjrct.Answer to be true in schoolAnswer
    }

    public AnswerAdapter getSchoolAnswer() {
        return schoolAnswerAdapter;
    }

    public String getCorrectAns() {
        return schoolAnswerAdapter.getAnswer();
    }

    public void setSchoolAnswer(AnswerAdapter schoolAnswerAdapter) {
        this.schoolAnswerAdapter = schoolAnswerAdapter;
    }

    @Override
    public String toStringForTestRes() {
        StringBuffer sB = new StringBuffer(super.toString());
        sB.append("--->  " + getCorrectAns());
        return sB.toString();
    }

    @Override
    public String toStringForTest() {
        StringBuffer sB = new StringBuffer(super.toString() + '\n');
        sB.append("--->  ________________________________________________________\n"
                + "      ________________________________________________________\n"
                + "      ________________________________________________________\n");

        return sB.toString();
    }

    public String toString() {
        StringBuffer sB = new StringBuffer(super.toString());
        sB.append("--->  " + getCorrectAns());
        return sB.toString();
    }

    public boolean equals(openQuery q) {
        return super.equals(q) && (q.getSchoolAnswer().equals(schoolAnswerAdapter));
    }
}
