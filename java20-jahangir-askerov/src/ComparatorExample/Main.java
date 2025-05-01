package ComparatorExample;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = new User(22, "Cahnagir");
        User user2 = new User(21, "Kamran");
        User user3 = new User(21, "Ali");
        User user4 = new User(26, "Cuma");

        List<User> users = new ArrayList<>(List.of(user1, user2, user3, user4));

        Sorter.sortByAgeAndName(users);

        for(User user : users){
            System.out.println(user.getName() + " " + user.getAge());
        }
    }
}
