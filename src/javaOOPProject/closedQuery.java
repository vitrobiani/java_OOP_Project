package javaOOPProject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class closedQuery extends Query implements Iterable<AnswerAdapter> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final int maxAns = 10;
    private static final int minAns = 3;

    private ArrayList<AnswerAdapter> answerAdapterList;

    public closedQuery(String q, difficulty diff, QueryRepository QR, ArrayList<AnswerAdapter> answerAdapterList) {
        super(q, diff, QR);
        this.answerAdapterList = answerAdapterList;
    }

    public closedQuery(String q, difficulty diff, QueryRepository QR) {
        super(q, diff, QR);
    }

    public boolean isAnswerInQuery(AnswerAdapter cA) {
        return answerAdapterList.contains(cA);
    }

    public boolean addAnswerToQuery(int AnsI, boolean isCorrect, AnswerRepository AR) {
        AnswerAdapter cA = new AnswerAdapter(AnsI, isCorrect, AR, AnswerAdapter.eType.close);
        if (!(isAnswerInQuery(cA)) && answerAdapterList.size() <= maxAns) {
            answerAdapterList.add(cA);
            return true;
        }
        return false;
    }

    public boolean addAnswerToQuery(AnswerAdapter cA) throws Exception {
        if ((isAnswerInQuery(cA)) || (answerAdapterList.size() >= maxAns)) {
            throw new AnswerException();
        }
        answerAdapterList.add(cA);
        return true;
    }

    public void rmAnAnswer(int aIndex) {
        answerAdapterList.remove(aIndex);
    }

    public int getMaxAns() {
        return maxAns;
    }

    public ArrayList<AnswerAdapter> getAnswerList() {
        return answerAdapterList;
    }

    public ArrayList<String> getStringAnswerList() {
        ArrayList<String> StringAnswerList = new ArrayList<>();

        for (int i = 0; i < StringAnswerList.size(); i++) {
            StringAnswerList.add(answerAdapterList.get(i).getAnswer());
        }
        return StringAnswerList;
    }

    public void setAnswerList(ArrayList<AnswerAdapter> answerAdapterList) {
        this.answerAdapterList = answerAdapterList;
    }

    @Override
    public String toStringForTestRes() {
        StringBuffer sB = new StringBuffer(super.toString());
        int howManyCorrect = 0;
        for (int i = 0; i < answerAdapterList.size(); i++) {
            if (answerAdapterList.get(i).isCorrect()) {
                howManyCorrect++;
            }
        }
        int i = 0;
        for (; i < answerAdapterList.size(); i++) {
            sB.append("    " + (i + 1) + ")  " + answerAdapterList.get(i).getAnswer()
                    + ((answerAdapterList.get(i).isCorrect() && (howManyCorrect == 1)) ? "  |T|" : "  |F|") + '\n');
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
        ArrayList<Integer> printRandom = randomArrayInRange(answerAdapterList.size());

        StringBuffer sB = new StringBuffer(super.toString());
        int i = 0;
        for (; i < answerAdapterList.size(); i++) {
            sB.append("    " + (i + 1) + ")  " + answerAdapterList.get(printRandom.get(i) - 1).getAnswer() + '\n');
        }
        sB.append("    " + (++i) + ")  some of the answers are correct" + '\n');
        sB.append("    " + (++i) + ")  none of the answers are correct" + '\n');

        return sB.toString();
    }

    public String toString() {
        StringBuffer sB = new StringBuffer(super.toString());

        int i = 0;
        for (AnswerAdapter a : answerAdapterList) {
            sB.append("    " + (i + 1) + ")  " + a.getAnswer() + ((a.isCorrect()) ? "  |T|" : "  |F|") + '\n');
            i++;
        }

        return sB.toString();
    }

    public boolean equals(closedQuery q) {
        boolean b = true;
        for (int i = 0; i < answerAdapterList.size(); i++) {
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
    public Iterator<AnswerAdapter> iterator() {
        return new GenericIterator<>(answerAdapterList);
    }

}
