package elements;

import java.lang.reflect.InvocationTargetException;

public class FindElement {


    public static <T extends Element> T elementFor(Class<T> t) {
        try {
            return t.getDeclaredConstructor(t).newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }



}
