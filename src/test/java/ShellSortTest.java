import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ShellSortTest {
    @Test
    @DisplayName("Check sort with any values")
    void checkSorting() {
        assertAll(
                () -> assertArrayEquals(new int[] {1}, ShellSort.sort(new int[] {1})),
                () -> assertArrayEquals(new int[] {1, 2, 100}, ShellSort.sort(new int[] {1, 2, 100})),
                () -> assertArrayEquals(new int[]{3, 4, 5, 33}, ShellSort.sort(new int[]{33, 3, 4, 5})),
                () -> assertArrayEquals(new int[]{1, 3, 4, 7, 9}, ShellSort.sort(new int[]{4, 1, 3, 9, 7})),
                () -> assertArrayEquals(new int[]{0, 0, 0, 0}, ShellSort.sort(new int[]{0, 0, 0, 0})),
                () -> assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ShellSort.sort(new int[]{5, 4, 3, 2, 1})),
                () -> assertArrayEquals(new int[]{3, 4, 12, 13, 14, 18, 21, 27, 33, 38, 41, 55, 57, 63, 70, 72, 73, 73, 74, 76, 80, 82, 84, 86, 99},
                        ShellSort.sort(new int[]{21, 72, 13, 27, 14, 73, 18, 63, 3, 41, 33, 76, 55, 99, 57, 80, 73, 86, 38, 70, 12, 84, 4, 74, 82}))
        );
    }

    @Test
    @DisplayName("Empty array")
    void checkEmpty() {
        assertArrayEquals(new int[]{}, ShellSort.sort(new int[]{}));
    }
    @Test
    @DisplayName("Null exception")
    void checkNull() {
        assertThrows(NullPointerException.class, () -> ShellSort.sort(null));
    }
}
