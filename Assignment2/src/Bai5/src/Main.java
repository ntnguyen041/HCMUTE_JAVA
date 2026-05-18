package Assignment2.src.Bai5.src;
import Assignment2.src.Bai5.src.model.Product;
import java.util.List;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Smartphone", 499.99, 50));
        products.add(new Product("TV", 799.99, 30));

        for (Product product : products) {
             product.displayInfo();
             System.out.println();
        }
       
       
        System.out.println("Total Products: " + Product.getTotalProducts());
        System.out.println("Total Revenue: $" + Product.getTotalRevenue());


        products.get(1).applyPromotion(10);
        products.get(2).sell(5);
        products.get(3).restock(10);

        for (Product product : products) {
             product.displayInfo();
             System.out.println();
        }

        Product.applyGlobalPromotion(products.toArray(new Product[0]), 10);
        System.out.println("Total Revenue after promotion: $" + Product.getTotalRevenue());

        // Khi sản phẩm không còn bán nữa,
        // totalProducts nên giảm nếu biến này dùng để
        // đếm số sản phẩm còn hoạt động trong hệ thống.
        //
        // Tuy nhiên không nên xoá object hoàn toàn,
        // vì vẫn cần lưu lịch sử hoá đơn, thống kê,
        // hoặc dữ liệu bán hàng cũ.
        //
        // Vì vậy ta dùng biến discontinued để đánh dấu
        // sản phẩm đã ngừng kinh doanh thay vì xoá hẳn.
    }
}
