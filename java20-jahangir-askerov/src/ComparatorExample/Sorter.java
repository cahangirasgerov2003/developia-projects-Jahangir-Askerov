package ComparatorExample;

import java.util.Comparator;
import java.util.List;

public class Sorter {

    public static void sortByAgeAndName(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getAge() == o2.getAge()) {
                    return o1.getName().compareTo(o2.getName());
                }
                return o2.getAge() - o1.getAge();
            }
        });
    }

}
