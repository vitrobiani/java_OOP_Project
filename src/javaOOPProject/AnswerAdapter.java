package javaOOPProject;

import java.io.Serializable;
import javaOOPProject.hw3_Answer.eType;

public class AnswerAdapter implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private hw3_Answer Answer;

    // for the closed questions - need to set isCorrect
    public AnswerAdapter(int AnsI, boolean isCorrect, AnswerRepository AR, eType type) {
        Answer = new hw3_Answer(AnsI, isCorrect, AR, type);
    }

    // for the open question - school answer is always true
    public AnswerAdapter(int AnsI, AnswerRepository AR, eType type) {
        Answer = new hw3_Answer(AnsI, AR, type);
    }

    public int getAnswerIndex() {
        return Answer.hw3_getAnswerIndex();
    }

    public boolean isCorrect() {
        return Answer.hw3_isCorrect();
    }

    public void setIsCorrect(boolean bool) {
        Answer.hw3_setIsCorrect(bool);
    }

    public eType getType() {
        return Answer.hw3_getType();
    }

    public AnswerRepository getAR() {
        return Answer.hw3_getAR();
    }

    public String getAnswer() {
        return Answer.hw3_getAnswer();
    }

    public String toString() {
        return Answer.hw3_toString();
    }

    public boolean equals(AnswerAdapter a) {
        return Answer.hw3_equals(a);
    }
}
