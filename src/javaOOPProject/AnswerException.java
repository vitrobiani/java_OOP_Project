package javaOOPProject;

@SuppressWarnings("serial")
public class AnswerException extends Exception {
    String message = "To many answers";

    public String getMessage() {
        return message;
    }
}
