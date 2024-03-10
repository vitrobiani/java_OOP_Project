package javaOOPProject;

import java.time.Clock;

import static javaOOPProject.main.addNewAnswerToRepository_3;

public class addAnswerCommand extends MenuActionCompleteListener implements Command{

    @Override
    public void execute() {
        addNewAnswerToRepository_3();
        update("Answer added successfully!");
    }
}
