package javaOOPProject;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;


public class main {
    public static Scanner s = new Scanner(System.in);
    public static int SubIn = 0;
    public static SubjectRepository SR = SubjectRepository.getInstance();
    public static fileFunctions ff = new fileFunctions();
    public static Services SRV = new Services(SR);

    public static void main(String[] args) throws Exception {
        try {
            hello();
        } catch (FileNotFoundException e) {
            System.out.println("no backup files found");
        } catch (Exception e1) {
            System.out.println("an error happend loading the backup");
            System.out.println(e1.getMessage());
        }
        SRV.setSR(SR);
//        fillTheFile();
        subjectLobby();

    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static <T> T checkInput(Predicate<T> condition, String mesg) {
        T choice = null;

        try {
            do {
                System.out.println(mesg);
                String input = s.next();
                try {
                    if (condition instanceof Predicate<?> && isInteger(input)) {
                        choice = (T) (Integer) Integer.parseInt(input);
                    } else if (condition instanceof Predicate<?>) {
                        if (input.length() == 1) {
                            choice = (T) (Character) input.charAt(0);
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please try again");
                    s.next(); // consume the invalid input
                } catch (Exception e) {
                    System.out.println("Please try again");
                }

                if (choice != null && condition.test(choice)) {
                    System.out.println("Invalid input. Please try again");
                }
            } while (choice == null || condition.test(choice));
        }catch (Exception e){
            System.out.println("an error occured, try again");
            return checkInput(condition, mesg);
        }

        s.nextLine();
        return choice;
    }

    public static void subjectLobby() throws Exception {
        System.out.println(
                "1. choose a subject \n" + "2. create a new subject \n" + "3. delete a subject\n" + "4. exit and save");

        Predicate<Integer> p = i -> (i < 0 || i > 4);
        Integer choise = (Integer) checkInput(p, "");

        switch (choise) {
            case 1: {
                subjectChoose();
                break;
            }
            case 2: {
                createNewSubject();
                break;
            }
            case 3: {
                rmSubject();
                break;
            }
            case 4: {
                goodbye();
            }
        }
    }

    public static void subjectChoose() throws Exception {
        System.out.println("what subject would you like to choose: <0 to go back>\n");

        Predicate<Integer> p = i -> (i >= SR.length() + 1 || i <= -1);
        SubIn = checkInput(p, SR.toString()) - 1;

        if (SubIn == -1)
            subjectLobby();

        lobby();

    }

    public static void createNewSubject() throws Exception {
        char add;
        do {
            s.nextLine();
            System.out.println("what is the name of the subject:  \n");
            String subName = s.nextLine();
            Subject sub = new Subject(subName);
            if (SR.addItemToRepo(sub))
                System.out.println("added!");
            System.out.println(SR.toString());

            System.out.println("would you like to add another <y/n>:  ");
            add = s.next().charAt(0);

        } while (add != 'n');
        subjectLobby();
    }

    public static void rmSubject() throws Exception {
        System.out.println(SR.toString());

        Predicate<Integer> p = i -> (i < 1 || i > SR.getRepo().size() + 1);
        int choise = checkInput(p, "which one would you like to remove: ") - 1;

        SR.rmItem(choise);
        subjectLobby();
    }

    public static void lobby() throws Exception {
        int choise = 0;
        Command printAnswersCom = new printAllAnswersCommand(SR, SubIn);
        Command printQuerriesCom = new printAllQuerriesCommand(SR, SubIn);
        Command addAnswerCom = new addAnswerCommand();
        do {
//			SRV.setSR(SR);
            System.out.println(
                    "the subject: " + SR.getName(SubIn) + "\n hello what would you like to do? \n"
                            + "1. print out all the questions and its answers in the repository \n"
                            + "2. print all the answers in the repository" + "\n"
                            + "3. adding a new answer to the repository \n" + "4. adding a new question \n"
                            + "5. updating answers to a question (add, remove, update) \n"
                            + "6. removing a question from the repository \n" + "7. creating a test manually \n"
                            + "8. go back to subject choise \n" + "0. exit \n");

            Predicate<Integer> p = i -> (i < 0 || i > 8);
            choise = checkInput(p, "");

            switch (choise) {
                case 1: {
                    printQuerriesCom.execute();
                    break;
                }
                case 2: {
                    printAnswersCom.execute();
                    break;
                }
                case 3: {
                    addAnswerCom.execute();
                    break;
                }
                case 4: {
                    addQ_4();
                    break;
                }
                case 5: {
                    updatingAnAnswerToQues_5();
                    break;
                }
                case 6: {
                    rmQuestion_6();
                    break;
                }
                case 7: {
                    personalTest_7();
                    ;
                    break;
                }
                case 8: {
                    subjectChoose();
                    break;
                }
                case 0: {
                    goodbye();
                }
            }
        } while (choise != 0);
    }

    // printing all the questions and answers
    public static void printAll_1() {
        System.out.println("printing Querys");
        System.out.println(SRV.QRtoString(SubIn));
        System.out.println("finished printing");
    }

    // printing the answer repository
    public static void printAnsRep_2() {
        System.out.println("printing Answers!");
        System.out.println(SRV.ARtoString(SubIn));
        System.out.println("finished printing!");
    }

    // adding new answer to repository
    public static void addNewAnswerToRepository_3() {
        System.out.println("\nwrite the answer:  ");
        String newAns = s.nextLine();

        if (SRV.addAnswer(SubIn, newAns)) {
            System.out.println("added answer!");
        } else {
            System.out.println("answer already in repository");

        }
    }

    // adding a question to the Query repository
    public static void addQ_4() throws AnswerException {
        Predicate<Character> p = i -> (i != 'c' && i != 'o');
        char qType = checkInput(p, "what kind of question would you like to add - closed or open: <c/o>");

        System.out.println("how difficult will the question be:  ");
        for (int i = 0; i < Query.difficulty.values().length; i++) {
            System.out.println((i + 1) + ") " + Query.difficulty.values()[i]);
        }

        Predicate<Integer> pr = i -> (i >= Query.difficulty.values().length + 1 || i < 1);
        int diff = checkInput(pr, "")-1;

        System.out.println("what is the question: ");
        String Ques = s.nextLine();

        // giving a chance to add an answer
        Predicate<Character> pre = i -> (i != 'n' && i != 'y');
        char addAnsToRep;
        do {
            addAnsToRep = checkInput(pre, "would you like to add an answer to the repository: <y/n> ");
            if (addAnsToRep == 'y') {
                addNewAnswerToRepository_3();
            }
        } while (addAnsToRep != 'n');

        if (qType == 'c')
            addClosedQ_4(diff, Ques);
        else
            addOpenQ_4(diff, Ques);
    }

    public static void addClosedQ_4(int diff, String Ques) throws AnswerException {
        System.out.println("you can choose up to 10 answers maximum!\n");

        // setting up the Query
        ArrayList<AnswerAdapter> answerAdapterList = new ArrayList<AnswerAdapter>();
        closedQuery newClosedQuery = new closedQuery(Ques, Query.difficulty.values()[diff], SRV.getQR(SubIn),
                answerAdapterList);
        if (SRV.getQR(SubIn).addItemToRepo(newClosedQuery)) {
            System.out.println("Query added!");
        } else {
            System.out.println("Query already exists");
        }

        int addedAnswers = 0;
        char addAns = 0;
        do {
            if (addAnswerToClosedQuery_5(SRV.getQR(SubIn).length()-1)) {
                System.out.println("Answer Added!");
            }else {
                System.out.println("Answer Was Not Added!");
            }

            Predicate<Character> c = i -> (i != 'n' && i != 'y');
            if (addedAnswers > 3 && addedAnswers < 10) {
                addAns = checkInput(c,"would you like to add another answer? <y/n>");
            }else {
                addAns = 'n';
            }
        } while (addedAnswers < 3 || addAns != 'n');

    }

    public static void addOpenQ_4(int diff, String Ques) {
        openQuery newOpenQuery;

        System.out.println(SRV.ARtoString(SubIn));
        System.out.println("enter 0 to go back");


        Predicate<Integer> p = i -> ( i > SRV.getARlength(SubIn) || i < 0);
        int ansChoose = checkInput(p,"")-1;

        if (ansChoose == -1)
            addOpenQ_4(diff, Ques);

        AnswerAdapter newOpenAns = new AnswerAdapter(ansChoose, SRV.getAR(SubIn), AnswerAdapter.eType.open);
        newOpenQuery = new openQuery(Ques, Query.difficulty.values()[diff], SRV.getQR(SubIn), newOpenAns);

        if (SRV.getQR(SubIn).addItemToRepo(newOpenQuery)) {
            System.out.println("Query added!");
        } else {
            System.out.println("Query already exists");
        }
    }

    // updating is deleting and changing answer to Query
    public static void updatingAnAnswerToQues_5() {
        System.out.println(SRV.getQR(SubIn).toString());

        Predicate<Integer> p = i -> (i > SRV.getQR(SubIn).length() && i <= 0);
        int qIndex = checkInput(p, "what question would you like to add to or update: ")-1;
        System.out.println(SRV.getQuery(SubIn, qIndex).toString());

        if (SRV.getQuery(SubIn, qIndex).getClass().equals(closedQuery.class)) {
            Predicate<Character> c = i -> (i != 'r' && i != 'a');
            char addOrRm = checkInput(c , "would you like to remove or add an answer: <r/a>");

            if (addOrRm == 'a') {
                Predicate<Character> pr = i -> (i != 'n' && i != 'y');
                char addAnsToRep = checkInput(pr, "would you like to add an answer to the repository: <y/n> ");
                if (addAnsToRep == 'y')	addNewAnswerToRepository_3();
                addAnswerToClosedQuery_5(qIndex);
            } else {
                rmAnswerFromQues_5(qIndex);
            }
        } else {
            updateAnswerOpenQuery_5(qIndex);
        }
    }

    public static boolean addAnswerToClosedQuery_5(int qIndex) {
        closedQuery q = ((closedQuery) SRV.getQuery(SubIn, qIndex));

        Predicate<Character> p = i -> (i != 't' && i != 'f');
        char tOrF = checkInput(p, "is the answer you want to add true or false: <t/f>");

        boolean b = (tOrF == 't') ? true : false;

        System.out.println("choose an answer from the repository to the Query: ");
        System.out.println(SRV.getAR(SubIn).closedAnsLimitedtoString(q.getAnswerList()));

        Predicate<Integer> pr = i -> ((SR.getRepo(SubIn).getAR().isInLimitedArray(i, q.getAnswerList())));
        int ansToAdd = checkInput(pr, "")-1;

        AnswerAdapter nA = new AnswerAdapter(ansToAdd, b, SRV.getAR(SubIn), AnswerAdapter.eType.close);
        try {
            return ((closedQuery) SRV.getQuery(SubIn, qIndex)).addAnswerToQuery(nA);
        } catch (AnswerException e) {
            System.out.println(e.getMessage());
            return false;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void rmAnswerFromQues_5(int qIndex) {
        closedQuery cQ = ((closedQuery) SRV.getQuery(SubIn, qIndex));

        System.out.println(SRV.getQuery(SubIn, qIndex).toString());

        int ansToRm = -1;
        do {
            System.out.println("what answer would you like to remove: ");
            try {
                ansToRm = s.nextInt() - 1;
            } catch (InputMismatchException e) {
                System.out.println("enter a number");
                s.next();
            }
        } while (ansToRm > cQ.getAnswerList().size() || ansToRm < 0);

        cQ.rmAnAnswer(ansToRm);
        System.out.println("answer removed!");
    }

    public static void updateAnswerOpenQuery_5(int qIndex) {
        System.out.println("would you like to write a new answer or choose from repository: <w/r>");

        char newOrRep;
        do {
            newOrRep = s.next().charAt(0);
        } while (newOrRep != 'w' && newOrRep != 'r');

        if (newOrRep == 'w') {
            System.out.println("please write a new answer: \n");
            String newAns = s.nextLine();
            SRV.addAnswer(SubIn, newAns);
            AnswerAdapter nA = new AnswerAdapter(SRV.getAR(SubIn).length() - 1, SRV.getAR(SubIn),
                    AnswerAdapter.eType.open);
            ((openQuery) SRV.getQuery(SubIn, qIndex)).setSchoolAnswer(nA);
        } else {
            SRV.ARtoString(SubIn);
            int ansToAdd;
            do {
                System.out.println("choose an answer from the repository: ");
                ansToAdd = s.nextInt();
            } while (ansToAdd < SRV.getARlength(SubIn));
            AnswerAdapter nA = new AnswerAdapter(ansToAdd, SRV.getAR(SubIn), AnswerAdapter.eType.open);
            ((openQuery) SRV.getQuery(SubIn, qIndex)).setSchoolAnswer(nA);
        }
    }

    // removing a question
    public static void rmQuestion_6() {
        System.out.println(SRV.getQR(SubIn).toString());

        System.out.println("what Query would you like to remove: ");

        Predicate<Integer> p = i -> (i < 0 || i > SRV.getQR(SubIn).length());
        int qToRm = checkInput(p, "");

        SRV.getQR(SubIn).rmItem(qToRm);
    }

    // creating a personal test
    public static void personalTest_7() throws Exception {
        System.out.println("how many questions will there be in the test: ");
        int questionAmount = 0;
        do {
            try {
                questionAmount = s.nextInt();
                if (questionAmount < 10) {
                    throw new ExamException();
                }
            } catch (InputMismatchException e) {
                System.out.println("enter a number");
                s.next();
            } catch (ExamException e1) {
                System.out.println(e1.getMessage());
            }
        } while (questionAmount <= 0 || questionAmount > SRV.getQR(SubIn).length());

        System.out.println("would you like to do an automatic exam or manualy create one <a/m>");
        char autoOrMan;
        do {
            autoOrMan = s.next().charAt(0);
        } while (autoOrMan != 'a' && autoOrMan != 'm');

        createExam ce = new createExam(SR.getRepo(SubIn), questionAmount);

        if (autoOrMan == 'm') {
            manualExam(questionAmount, ce);
        } else {
            ce.AutomaticExam();
        }
    }

    public static closedQuery closedQueryWithSpesificAns(QueryRepository Querries, int i) throws Exception {
        closedQuery q = (closedQuery) Querries.getRepo(i);
        closedQuery copyQ = new closedQuery(q.getQues(), q.getDiff(), q.getQR(), q.getAnswerList());
        ArrayList<AnswerAdapter> al = new ArrayList<>();
        q.setAnswerList(al);
        char b = 0;
        do {
            System.out.println(copyQ.toString());
            System.out.println("what answers from the Query would you like to be presented in the test:  ");

            Predicate<Integer> p = j -> ( j < 0 || j >= copyQ.getAnswerList().size());
            int ansToAdd = checkInput(p, "");

            q.addAnswerToQuery(copyQ.getAnswerList().get(ansToAdd));
            copyQ.rmAnAnswer(ansToAdd);
            System.out.println("would you like to pick another one? <y/n>");
            b = s.next().charAt(0);
        } while (b != 'n' && copyQ.getAnswerList().size() > 0);
        return q;
    }

    public static void manualExam(int questionAmount, createExam ce) throws Exception {
        QueryRepository examQuerries = new QueryRepository();
        QueryRepository copyQueryRep = new QueryRepository(SRV.getAllQueryArray(SubIn));

        while (questionAmount > 0) {

            Predicate<Integer> p = QToAdd -> (QToAdd < 0 || QToAdd > copyQueryRep.length());
            int QToAdd = checkInput(p, copyQueryRep.toString() + "choose a Query to add to the test:  ");

            if (copyQueryRep.getRepo(QToAdd) instanceof closedQuery) {
                closedQuery q = closedQueryWithSpesificAns(copyQueryRep, QToAdd);
                if (examQuerries.addItemToRepo(q)) {
                    System.out.println("added!");
                    copyQueryRep.rmItem(QToAdd);
                    questionAmount--;
                }
            } else {
                if (examQuerries.addItemToRepo(copyQueryRep.getRepo(QToAdd))) {
                    System.out.println("added!");
                    copyQueryRep.rmItem(QToAdd);
                    questionAmount--;
                }
            }
        }
        ce.setExamQueries(examQuerries);
        ce.ManualExam();
    }

    public static void hello() throws FileNotFoundException, IOException, ClassNotFoundException {
        SR = (SubjectRepository) ff.loadRepository();
    }

    public static void goodbye() throws FileNotFoundException, IOException {
        ff.saveRepository(SR);
        System.out.println("goodbye :)");
    }

    public static ArrayList<Integer> randomArrayInRange(int range) {
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

    public static void fillTheFile() {
        for (int i = 1; i < 4; i++) {
            Subject s = new Subject("sub " + i);

            if(SR.addItemToRepo(s)){
            }
        }
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 10; i++) {
                SRV.getAR(j).addItemToRepo("answer " + i);
            }
        }
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 9; i++) {
                ArrayList<Integer> ansToAdd = randomArrayInRange(9);
                for (int w = 0; w < 3; w++)
                    ansToAdd.remove(w);
                ArrayList<AnswerAdapter> al = new ArrayList<>();
                closedQuery q = new closedQuery("closed " + i, Query.difficulty.medium, SRV.getQR(j), al);
                for (int k = 0; k < 5; k++) {
                    Boolean bool = (k % 2 == 0) ? (true) : (false);
                    q.addAnswerToQuery(ansToAdd.get(k), bool, SRV.getAR(j));
                }

                SR.getRepo(j).getQR().addItemToRepo(q);
            }
            ArrayList<Integer> ansToAdd = randomArrayInRange(9);
            for (int i = 0; i < 9; i++) {
                AnswerAdapter a = new AnswerAdapter(ansToAdd.get(i), SRV.getAR(j), AnswerAdapter.eType.open);
                openQuery q = new openQuery("open " + i, Query.difficulty.hard, SRV.getQR(j), a);
                SRV.addQuery(q, j);
            }
        }
    }

}//you bloody bastard
