package javaOOPProject;

import java.io.Serializable;

public class hw3_Answer implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int AnswerIndex;
    protected boolean isCorrect;

    public enum eType {
        open, close
    };

    protected hw3_Answer.eType type;
    private AnswerRepository AR;

    // for the closed questions - need to set isCorrect
    public hw3_Answer(int AnsI, boolean isCorrect, AnswerRepository AR, hw3_Answer.eType type) {
        AnswerIndex = AnsI;
        this.isCorrect = isCorrect;
        this.AR = AR;
        this.type = type;
    }

    // for the open question - school answer is always true
    public hw3_Answer(int AnsI, AnswerRepository AR, hw3_Answer.eType type) {
        AnswerIndex = AnsI;
        this.AR = AR;
        this.type = type;
        this.isCorrect = true;
    }

    public int hw3_getAnswerIndex() {
        return AnswerIndex;
    }

    public boolean hw3_isCorrect() {
        return isCorrect;
    }

    public void hw3_setIsCorrect(boolean bool) {
        isCorrect = bool;
    }

    public hw3_Answer.eType hw3_getType() {
        return type;
    }

    public AnswerRepository hw3_getAR() {
        return AR;
    }

    public String hw3_getAnswer() {
        return AR.getRepo(AnswerIndex);
    }

    public String hw3_toString() {
        StringBuffer sB = new StringBuffer();
        sB.append("the answer: " + AR.getRepo(AnswerIndex)
                + '\n' + "it is: " + (isCorrect) != null ? "correct": "incorrect"
                + '\n' + "its type is: " + type);

        return sB.toString();
    }

    public boolean hw3_equals(AnswerAdapter a) {
        if (a.getType() == type && a.getAnswerIndex() == AnswerIndex && a.getType() == type) {
            return true;
        }
        return false;
    }
}
