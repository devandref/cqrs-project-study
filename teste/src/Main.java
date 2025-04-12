import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>();
        integerList.add(0);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);

        List<Integer> integerNewList = new ArrayList<>();

        for (int i = integerList.size() - 1; i <= integerList.size(); i--) {
            if (integerList.size() != integerNewList.size()) {
                integerNewList.add(integerList.get(i));
                System.out.println(i);
            }

        }
    }
}