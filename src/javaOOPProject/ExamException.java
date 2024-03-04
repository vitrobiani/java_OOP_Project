package javaOOPProject;

public class ExamException extends Exception {
    String message = "to many questions";

    public String getMessage() {
        return message;
    }
}
