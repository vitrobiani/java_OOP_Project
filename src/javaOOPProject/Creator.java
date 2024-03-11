package javaOOPProject;

public interface Creator<T> {
    public T create(String str ,QueryRepository QR , AnswerRepository AR, Query.difficulty diff, hw3_Answer.eType type);
}
