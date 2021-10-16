package manager;

import java.util.ArrayList;
import java.util.List;

public class SomethingManager {
    private final List<String> somethings = new ArrayList<>();


    public void addSomething(String something) {
        this.somethings.add(something);
    }

    public List<String> getSomethings() {

        return somethings;
    }
}
