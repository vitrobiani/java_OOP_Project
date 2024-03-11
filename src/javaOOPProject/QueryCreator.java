package javaOOPProject;

import java.util.ArrayList;

public class QueryCreator implements Creator<Query>{
    @Override
    public Query create(String question, QueryRepository QR , AnswerRepository AR, Query.difficulty diff, hw3_Answer.eType type) {
        Creator c = new AnswerCreator();
        switch (type) {
            case open:
                AnswerAdapter cA = (AnswerAdapter) c.create("", QR, AR, diff, type);
                return new openQuery(question, diff, QR, cA);
            case close:
                ArrayList<AnswerAdapter> answerAdapterList = new ArrayList<>();
                return new closedQuery(question, diff, QR, answerAdapterList);
            default:
                return null;
        }
    }
}
