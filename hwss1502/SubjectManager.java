package hwss1502;

import java.util.ArrayList;

public class SubjectManager implements manager<Subject> {
    ArrayList<Subject> list = new ArrayList<Subject>();

    @Override
    public void add(Subject item) {
        list.add(item);
    }

    @Override
    public void remove(Subject item) {
        list.remove(item);
    }

    @Override
    public void display() {
        for (Subject item : list) {
            System.out.println(item.toString());
        }
    }
}
