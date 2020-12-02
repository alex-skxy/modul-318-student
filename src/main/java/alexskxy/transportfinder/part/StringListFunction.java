package alexskxy.transportfinder.part;

import java.util.List;

@FunctionalInterface
public interface StringListFunction {
    List<String> getStringList(String string);
}
