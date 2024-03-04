package javaOOPProject;

import java.io.Serializable;
import java.util.ArrayList;

public class AnswerRepository extends Repository<String> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AnswerRepository () {
        repo = new ArrayList<>();
    }

    public void updateAnswer(int i, String nA) {
        repo.remove(i);
        repo.add(i, nA);
    }

    // so that the user won't see the answer he already chose
    public String closedAnsLimitedtoString(ArrayList<Answer> AnswerList) {
        StringBuffer A = new StringBuffer();
        A.append("\n the closed answers: \n");
        for (int i = 0, j = 0; i < length(); i++) {
            if (j > AnswerList.size()-1 || !isInLimitedArray(i, AnswerList)) {
                String temp = ((i + 1) + ")  " + repo.get(i) + "\n");
                A.append(temp);
            }
        }
        return A.toString();
    }

    // Receives an answer array and returns an int array of the indexes in the rep
    // Important for showing only the unchosen answers when creating a query
    public ArrayList<Integer> closedAnsLimitedArray(ArrayList<Answer> AnswerList) {
        ArrayList<Integer> newAnswerList = new ArrayList<>();

        for (int i = 0; i < AnswerList.size(); i++) {
            newAnswerList.add(AnswerList.get(i).getAnswerIndex());
        }
        return newAnswerList;

    }

    public boolean isInLimitedArray(int i, ArrayList<Answer> AnswerList) {
        for (int j = 0; j < AnswerList.size(); j++) {
            if (i == AnswerList.get(j).getAnswerIndex())
                return true;
        }
        return false;
    }

    public String toString() {
        StringBuffer A = new StringBuffer();
        int i = 0;
        for (String s : repo) {
            A.append(((i + 1) + ")  " + s + "\n"));
            i++;
        }

        return A.toString();
    }

}
