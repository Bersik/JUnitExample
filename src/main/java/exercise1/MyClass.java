package exercise1;

/**
 * Created on 19:56 26.04.2016
 *
 * @author Bersik (Serhii Kisilchuk)
 */
public class MyClass {
    public int multiply(int x, int y) {
        // the following is just an example
        if (x > 999) {
            throw new IllegalArgumentException("X should be less than 1000");
        }
        return x * y;

    }
}
