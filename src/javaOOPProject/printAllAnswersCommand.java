package javaOOPProject;

import java.awt.*;

public class printAllAnswersCommand extends MenuActionCompleteListener implements Command{

    private SubjectRepository SR;
    private int SubIn;
    public printAllAnswersCommand(SubjectRepository SR, int SubIn) {
        this.SR = SR;
        this.SubIn = SubIn;
    }

    @Override
    public void execute() {
        System.out.println(SR.getRepo(SubIn).getAR().toString());
        update("All answers printed successfully!");
    }

}
