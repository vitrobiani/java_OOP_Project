package javaOOPProject;

public class printAllQuerriesCommand extends MenuActionCompleteListener implements Command {
    private SubjectRepository SR;
    private int SubIn;

    public printAllQuerriesCommand(SubjectRepository SR, int SubIn) {
        this.SR = SR;
        this.SubIn = SubIn;
    }

    @Override
    public void execute() {
        System.out.println(SR.getRepo(SubIn).getQR().toString());
        update("All queries printed successfully!");
    }
}
