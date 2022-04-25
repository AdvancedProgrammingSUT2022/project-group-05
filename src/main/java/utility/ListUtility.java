package utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtility{
    public static boolean isEqual(List<String> list1, List<String> list2) {
        ArrayList<String > list1Copy = new ArrayList<>(list1);
        ArrayList<String> list2Copy = new ArrayList<>(list2);

        Collections.sort(list1Copy);
        Collections.sort(list2Copy);

        return list1Copy.equals(list2Copy);
    }
}
