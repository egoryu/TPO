import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MethodTest {
    double eps = 0.01;
    @ParameterizedTest
    @ValueSource(doubles = {-1.1, -5.0})
    public void negativeMoreCheck(double value) {
        Assertions.assertEquals(Math.acos(value), Method.arcCos(value, 30), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.4, -0.9})
    public void negativeCheck(double value) {
        Assertions.assertEquals(Math.acos(value), Method.arcCos(value, 30), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, -0.1, 0.001, 0.1, 0.0})
    public void zeroCloseCheck(double value) {
        Assertions.assertEquals(Math.acos(value), Method.arcCos(value, 30), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.5, 0.9})
    public void positiveCheck(double value) {
        Assertions.assertEquals(Math.acos(value), Method.arcCos(value, 30), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.1, 5.0})
    public void positiveMoreCheck(double value) {
        Assertions.assertEquals(Math.acos(value), Method.arcCos(value, 30), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 1.0 + 0.001, 1.0 - 0.001, 1.0 - 0.01, 1.0 - 0.1})
    public void plusOneCheck(double value) {
        Assertions.assertEquals(Math.acos(value), Method.arcCos(value, 30), eps * 20);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -1.0 - 0.001, -1.0 + 0.001, -1.0 + 0.01, -1.0 + 0.1})
    public void minusOneCheck(double value) {
        Assertions.assertEquals(Math.acos(value), Method.arcCos(value, 30), eps * 20);
    }
}
