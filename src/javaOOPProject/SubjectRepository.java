package javaOOPProject;

import java.io.Serializable;
import java.util.ArrayList;

public class SubjectRepository extends Repository<Subject> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static SubjectRepository[] _instance = new SubjectRepository[1];


    private SubjectRepository() {
        repo = new ArrayList<>();
    }

    public static SubjectRepository getInstance() {
        if (_instance[0] == null) {
            _instance[0] = new SubjectRepository();
        }
        return _instance[0];
    }

    public String getName(int i) {
        return repo.get(i).getName();
    }

    public void setName(String name, int i) {
        repo.get(i).setName(name);
    }


    @Override
    public String toString() {
        StringBuffer s = new StringBuffer("\nThe Subjects in the repository:\n");

        int i = 0;
        for (Subject sub : repo) {
            s.append((i + 1) + ")  " + sub.getName() + '\n');
            i++;
        }

        return s.toString();
    }

}
