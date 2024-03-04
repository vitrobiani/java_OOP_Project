package javaOOPProject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class closedQuery extends Query implements Iterable<Answer> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final int maxAns = 10;
    private static final int minAns = 3;

    private ArrayList<Answer> AnswerList;

    public closedQuery(String q, difficulty diff, QueryRepository QR, ArrayList<Answer> AnswerList) {
        super(q, diff, QR);
        this.AnswerList = AnswerList;
    }

    public closedQuery(String q, difficulty diff, QueryRepository QR) {
        super(q, diff, QR);
    }

    public boolean isAnswerInQuery(Answer cA) {
        return AnswerList.contains(cA);
    }

    public boolean addAnswerToQuery(int AnsI, boolean isCorrect, AnswerRepository AR) {
        Answer cA = new Answer(AnsI, isCorrect, AR, Answer.eType.close);
        if (!(isAnswerInQuery(cA)) && AnswerList.size() <= maxAns) {
            AnswerList.add(cA);
            return true;
        }
        return false;
    }

    public boolean addAnswerToQuery(Answer cA) throws Exception {
        if ((isAnswerInQuery(cA)) || (AnswerList.size() >= maxAns)) {
            throw new AnswerException();
        }
        AnswerList.add(cA);
        return true;
    }

    public void rmAnAnswer(int aIndex) {
        AnswerList.remove(aIndex);
    }

    public int getMaxAns() {
        return maxAns;
    }

    public ArrayList<Answer> getAnswerList() {
        return AnswerList;
    }

    public ArrayList<String> getStringAnswerList() {
        ArrayList<String> StringAnswerList = new ArrayList<>();

        for (int i = 0; i < StringAnswerList.size(); i++) {
            StringAnswerList.add(AnswerList.get(i).getAnswer());
        }
        return StringAnswerList;
    }

    public void setAnswerList(ArrayList<Answer> answerList) {
        AnswerList = answerList;
    }

    @Override
    public String toStringForTestRes() {
        StringBuffer sB = new StringBuffer(super.toString());
        int howManyCorrect = 0;
        for (int i = 0; i < AnswerList.size(); i++) {
            if (AnswerList.get(i).isCorrect()) {
                howManyCorrect++;
            }
        }
        int i = 0;
        for (; i < AnswerList.size(); i++) {
            sB.append("    " + (i + 1) + ")  " + AnswerList.get(i).getAnswer()
                    + ((AnswerList.get(i).isCorrect() && (howManyCorrect == 1)) ? "  |T|" : "  |F|") + '\n');
        }
        sB.append("    " + (++i) + ")  some of the answers are correct" + ((howManyCorrect > 1) ? "  |T|" : "  |F|")
                + '\n');
        sB.append("    " + (++i) + ")  none of the answers are correct" + ((howManyCorrect == 0) ? "  |T|" : "  |F|")
                + '\n');

        return sB.toString();
    }

    public ArrayList<Integer> randomArrayInRange(int range) {
        Random r = new Random();
        ArrayList<Integer> printRandom = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            int temp;
            do {
                temp = r.nextInt(1, range + 1);
            } while (printRandom.contains(temp));
            printRandom.add(temp);
        }
        return printRandom;
    }

    @Override
    public String toStringForTest() {
        ArrayList<Integer> printRandom = randomArrayInRange(AnswerList.size());

        StringBuffer sB = new StringBuffer(super.toString());
        int i = 0;
        for (; i < AnswerList.size(); i++) {
            sB.append("    " + (i + 1) + ")  " + AnswerList.get(printRandom.get(i) - 1).getAnswer() + '\n');
        }
        sB.append("    " + (++i) + ")  some of the answers are correct" + '\n');
        sB.append("    " + (++i) + ")  none of the answers are correct" + '\n');

        return sB.toString();
    }

    public String toString() {
        StringBuffer sB = new StringBuffer(super.toString());

        int i = 0;
        for (Answer a : AnswerList) {
            sB.append("    " + (i + 1) + ")  " + a.getAnswer() + ((a.isCorrect()) ? "  |T|" : "  |F|") + '\n');
            i++;
        }

        return sB.toString();
    }

    public boolean equals(closedQuery q) {
        boolean b = true;
        for (int i = 0; i < AnswerList.size(); i++) {
            if (!(q.getAnswerList().get(i).equals(q.getAnswerList().get(i)))) {
                b = false;
            }
        }
        return (super.equals(q) && b);
    }

    public int getMinAns() {
        return minAns;
    }

    @Override
    public Iterator<Answer> iterator() {
        return new GenericIterator<>(AnswerList);
    }

}
