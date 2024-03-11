package javaOOPProject;

public class AnswerCreator implements Creator<AnswerAdapter>{

    @Override
    public AnswerAdapter create(String str, QueryRepository QR, AnswerRepository AR, Query.difficulty diff, hw3_Answer.eType type) {
        return new AnswerAdapter(0, AR, type);
    }
}
