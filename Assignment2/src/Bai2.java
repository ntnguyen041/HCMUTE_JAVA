package Assignment2.src;

import java.util.List;
import java.util.ArrayList;

class Rectangle {
    private double width;
    private double height;

    public Rectangle() {
        this.width = 1;
        this.height = 1;
    }

    public Rectangle(double site) {
        this.width = site;
        this.height = site;
    }

    public Rectangle(Rectangle other) {
        this.width = other.width;
        this.height = other.height;
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public boolean isSquare() {
        return width == height;
    }
    public double scale(double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("Scale factor must be positive.");
        }
        width *= factor;
        height *= factor;
        return getArea();
    }

    public void printInfo() {
        if (width <= 0 || height <= 0) {
            System.out.println("Invalid dimensions. Width and height must be positive.");
            return;
        }
        if (isSquare()) {
            System.out.println("This is a square.");
        } else {
            System.out.println("This is a rectangle.");
        }
        System.out.println("Area: " + getArea() + ", Perimeter: " + getPerimeter());
    }
}

public class Bai2 {
    public static void main(String[] args) {
        List<Rectangle> rectangles = new ArrayList<>();
        int[] sizes = { 5, 10};
        int width = 5;
        rectangles.add(new Rectangle());
        for (int size : sizes) {
            if(size == width) {
                rectangles.add(new Rectangle(size));
            } else {
                rectangles.add(new Rectangle(width, size));
            }
        }
        for (Rectangle rect : rectangles) {
            rect.scale(2.0);
            rect.printInfo();
            System.out.println();
        }
        System.out.println("Trong Java, không thể có 2 constructor giống hệt nhau về danh sách tham số vì Java sẽ không biết phải gọi constructor nào.");
    }
}
