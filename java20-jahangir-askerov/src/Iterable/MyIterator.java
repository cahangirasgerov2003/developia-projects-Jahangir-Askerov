package Iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyIterator {

    public void test() {
        List<String> list = new ArrayList<String>(List.of("Salam", "a", "aaa"));

        Iterator<String> iterator = list.iterator();

        if(iterator.hasNext()){
            String result = iterator.next();

            if(result.isEmpty()){
                iterator.remove();
            }

        }

    }
}
