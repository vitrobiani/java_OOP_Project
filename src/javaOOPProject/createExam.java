package javaOOPProject;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class createExam implements Examable {
    public static final String dir = System.getProperty("user.dir") + "\\test_files";
    private int questionAmount;
    private static final int autoAns = 4;
    private QueryRepository examQueries;
    private Subject sub;
    private fileFunctions ff;

    // for manual
    public createExam(QueryRepository examQueries, int amount) {
        this.examQueries = examQueries;
        questionAmount = amount;
        ff = new fileFunctions();
    }

    // for automatic exam
    public createExam(Subject sub, int amount) {
        this.sub = sub;
        questionAmount = amount;
    }

    @Override
    public void ManualExam() throws FileNotFoundException, Exception {
        writeTestToFile();
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
    public void AutomaticExam() throws Exception {
        QueryRepository examQuerries = new QueryRepository();
        QueryRepository copyQueryRep = new QueryRepository(sub.getQR().getRepo());

        ArrayList<Integer> randomQueryIndexArray = randomArrayInRange(questionAmount);

        for (int i = 0; i < randomQueryIndexArray.size(); i++) {
            int QToAdd = randomQueryIndexArray.get(i);
            if (copyQueryRep.getRepo(QToAdd) instanceof closedQuery) {
                closedQuery q = closedQueryWithSpesificAns((closedQuery) copyQueryRep.getRepo(QToAdd));
                if (examQuerries.addItemToRepo(q)) {
                    System.out.println("added query");
                }
            } else {
                if (examQuerries.addItemToRepo(copyQueryRep.getRepo(QToAdd))) {
                    System.out.println("added query");
                }
            }
        }
        setExamQueries(examQuerries);
        writeTestToFile();
    }

    public closedQuery closedQueryWithSpesificAns(closedQuery q) throws Exception {
        Random r = new Random();
        closedQuery copyQ = new closedQuery(q.getQues(), q.getDiff(), q.getQR(), q.getAnswerList());
        ArrayList<AnswerAdapter> answerAdapterList = new ArrayList<>();
        closedQuery copyq = new closedQuery(q.getQues(), q.getDiff(), q.getQR(), answerAdapterList);
        copyq.setAnswerList(answerAdapterList);

        ArrayList<Integer> randomAns = randomArrayInRange(autoAns);

        for (int j = 0; j < randomAns.size(); j++) {
            AnswerAdapter a = copyQ.getAnswerList().get(randomAns.get(j));
            copyq.addAnswerToQuery(a);
        }
        return copyq;
    }

    public void writeTestToFile() throws FileNotFoundException {
        // formating the time
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm");
        String rightNow = time.format(timeFormat);
        String fnRes = dir + "\\exam_res_" + rightNow + ".txt";
        String fn = dir + "\\exam_" + rightNow + ".txt";

        StringBuffer test = new StringBuffer();
        StringBuffer test_res = new StringBuffer();

        test.append("test\n");
        test_res.append(("test\n"));

        for (int i = 0; i < examQueries.length(); i++) {
            test.append(i + 1 + ")  " + examQueries.getRepo(i).toStringForTest() + "\n");
            test_res.append(i + 1 + ")  " + examQueries.getRepo(i).toStringForTestRes()+"\n");
        }

        ff.saveToFile(fn, test.toString());
        ff.saveToFile(fnRes, test_res.toString());

    }

    public int getQuestionAmount() {
        return questionAmount;
    }

    public void setQuestionAmount(int questionAmount) {
        this.questionAmount = questionAmount;
    }

    public QueryRepository getExamQueries() {
        return examQueries;
    }

    public void setExamQueries(QueryRepository examQueries) {
        this.examQueries = examQueries;
    }

    public Subject getSub() {
        return sub;
    }

    public void setSub(Subject sub) {
        this.sub = sub;
    }
}