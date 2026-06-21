package C5_D1_Assignment.src.utils;

import java.util.List;
public class LibraryUtils {

    // Tìm phần tử nhỏ nhất trong mảng (T extends Comparable<T>)
    public static <T extends Comparable<T>> T findMin(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        T min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(min) < 0) {
                min = array[i];
            }
        }
        return min;
    }

    // In mọi phần tử, sử dụng Unbounded Wildcard (?) thay cho (? extends Object)
    public static void printAll(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    // Sao chép danh sách, áp dụng nguyên tắc PECS (Producer Extends, Consumer
    // Super)
    public static <T> void copyList(List<? super T> dest, List<? extends T> src) {
        for (T item : src) {
            dest.add(item);
        }
    }
}
