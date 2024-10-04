/*
    packages này dùng để cho người dùng nhập thông tin vào
 */
package Management;

import dao.BrandList;
import dao.CategoryList;
import dao.ProductList;
import data.Brand;
import data.Category;
import data.Product;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import util.Inputter;
import dao.BrandDAO;
import dao.CategoryDAO;
import dao.ProductDAO;

public class bikeManagement {
//    prop
//    mình tham chiếu đến cái interface DAO để định nghĩa và sử dụng các phương thức
    private ProductDAO productDAO;
    private BrandDAO braDAO;
    private CategoryDAO cateDAO;
    private static final String PRODUCT_FILE = "Product.txt";
    
//    constructor

    public bikeManagement() {
        productDAO = new ProductList();
        braDAO = new BrandList();
        cateDAO = new CategoryList();
    }
    
//hàm thêm vào 1 đối tượng mới
    public void addProduct(){
//      thu thập thông tin ng dùng nè
        String proName = Inputter.getString("Enter name: ", "Cannot leave it blank");
        String brandID;
        do{
            brandID = Inputter.getString("Enter brandID : ", 
                    "Must enter form BXXX", "^B\\d{3}$");
            Brand brand = braDAO.getBrandByID(brandID);
//          sử dụng hàm getBrand bên brandList để kiểm tra xem trong file có
//          brandID này hong
            if(brand == null){
                System.out.println("BrandID not found in the list");
                System.out.println("In the list there are only:");
                braDAO.printFile();
                System.out.println("");
            }else{
                System.out.println(brand);
                break;
            }
        }while(true);   
        
        String categoryID;
        do{
            categoryID = Inputter.getString("Enter CategoryID: ",
                      "^C\\d{3}$");
            Category category = cateDAO.getCateByID(categoryID);
//          sử dụng hàm categoryID bên cateLi để kiểm tra xem trong file có
//          categoryID này hong
            if(category == null){
                System.out.println("CategoryID not found in the list: ");
                System.out.println("In the list there are only:");
                cateDAO.printFile();
                System.out.println("");
            }else{
                System.out.println(category);
                break;
            }
        }while(true);
        
        int modelYear = Inputter.getAnInteger("Enter ModelYear: ", "Must enter a number greater than 1 and less than 2025", 1000, 2024);
                                
        int listPrice = Inputter.getAnInteger("Enter price: ", "Cannot be left blank",
                                        1, Integer.MAX_VALUE);
        Product product = new Product(null, proName, brandID, categoryID, modelYear, listPrice);
        if(productDAO.addProduct(product)){// nếu trả giá trị true 
            System.out.println("More success");
        }else{
            System.err.println("Add failed!!");
        }
    }
//    hàm tìm kiếm bằng tên sản phẩm
    public void searchProByName(){
        String name = Inputter.getString("Enter name: ", "Cannot be left blank");
        List<Product> result = productDAO.searchProByName(name);
        if(result.isEmpty()){
            System.out.println("There are no products!!!");
        }else{
            result.sort(Comparator.comparingInt(Product::getModelYear));
            System.out.println("list after sorting");
//            so sánh theo modelYear của đề bài trước khi trả ra
            for (Product item : result) {
                System.out.println(item);
            }
        }
    }
    
    public void searchProByPrice() {
    //  Yêu cầu người dùng nhập giá từ và giá đến
    double minPrice = Inputter.getADouble("Enter minimum price: ", "Invalid price");
    double maxPrice = Inputter.getADouble("Enter maximum price: ", "Invalid price");
    
    // Gọi phương thức tìm kiếm sản phẩm theo giá trong ProductDAO
    List<Product> result = productDAO.searchProByPrice(minPrice, maxPrice);
    
    // Bước 3: Kiểm tra xem có tìm thấy sản phẩm hay không
    if (result.isEmpty()) {
        // Nếu không tìm thấy sản phẩm thì thông báo
        System.out.println("There are no products within the price range: " + minPrice + " - " + maxPrice);
    } else {
        // Nếu tìm thấy sản phẩm
        
        // Sắp xếp danh sách kết quả theo ModelYear từ thấp đến cao
        result.sort(Comparator.comparingInt(Product::getListPrice).reversed());
        
        // In ra danh sách sản phẩm đã sắp xếp
        System.out.println("Products found within price range: " + minPrice + " - " + maxPrice + " (sorted by Model Year):");
        for (Product item : result) {
            // Hiển thị thông tin của mỗi sản phẩm
            System.out.printf("ID: %s, Name: %s, Brand: %s, Category: %s, ModelYear: %d, Price: %d\n",
                              item.getProID(), 
                              item.getProName(),
                              braDAO.getBrandByID(item.getBrandID()).getBrandName(),
                              cateDAO.getCateByID(item.getCategoryID()).getCategoryName(),
                              item.getModelYear(), 
                              item.getListPrice());
        }
    }
}



   public void searchProByBrandID() {
    String brandID = Inputter.getString("Enter Brand ID: ", "Brand ID cannot be left blank");
    List<Product> result = productDAO.searchProByBrandID(brandID);
    
    if (result.isEmpty()) {
        System.out.println("There are no products for this brand ID: " + brandID);
    } else {
        result.sort(Comparator.comparingInt(Product::getModelYear).reversed());
        System.out.println("Products found for Brand ID: " + brandID + " (sorted by Model Year):");
        for (Product item : result) {
            System.out.printf("ID: %s, Name: %s, Brand: %s, Category: %s, ModelYear: %d, Price: %d\n",
                              item.getProID(), 
                              item.getProName(),
                              braDAO.getBrandByID(item.getBrandID()).getBrandName(),
                              cateDAO.getCateByID(item.getCategoryID()).getCategoryName(),
                              item.getModelYear(), 
                              item.getListPrice());
        }
    }
}




//    hàm cập nhật product bằng id của pro
    public void updateProduct(){
        String id = Inputter.getString("Enter the id to update: " 
                                , "^P\\d{3}$");
        Product product = productDAO.getProductByID(id);// lấy sản phẩm ra bằng hàm get
//        trống thì thông báo
        if(product == null){
            System.out.println("Product does not exist: ");
        }else{
//            nếu tồn tại thì cập nhật nì
            System.out.println("Products before update: " + product);
//            xử lý
            String newName = Inputter.getStringUpdate("Enter name information: ");
            
            String newBrandID;
            do{
            newBrandID = Inputter.getStringUpdateRegex("Enter newBrandID : ", 
                    "Must enter form BXXX ", "^B\\d{3}$");
//            nếu người dùng bỏ trống thì muốn giữ tt cũ nên bỏ qua
            if(newBrandID.isEmpty()){
                break;
            }
            Brand brand = braDAO.getBrandByID(newBrandID);
//          sử dụng hàm getBrand bên brandList để kiểm tra xem trong file có
//          brandID này hong
            if(brand == null){
                System.out.println("BrandID not found in the list");
            }else{
                System.out.println(brand);
                break;
            }
            }while(true);
            
            String newCateID;
            do{
            newCateID = Inputter.getStringUpdateRegex("Enter newCategoryID : ", 
                    "Must enter in the form CXXX", "^C\\d{3}$");
//            nếu người dùng bỏ trống thì muốn giữ tt cũ nên bỏ qua
            if(newCateID.isEmpty()){
                break;
            }
            Category category = cateDAO.getCateByID(newCateID);
//          sử dụng hàm getBrand bên brandList để kiểm tra xem trong file có
//          brandID này hong
            if(category == null){
                System.out.println("BrandID not found in the list");
            }else{
                System.out.println(category);
                break;
            }
            }while(true);
            int newModelYear = Inputter.getIntUpdate("Enter newModelYear: ", 
                    "must be between 1000-2024", "Not valid", 1000, 2024);
            int newListPrice = Inputter.getIntUpdate("Enter newListPrice: ", 
                    "Must > 0", "Not valid", 1, Integer.MAX_VALUE); 
            productDAO.updatePro(product, newName, newBrandID, newCateID, newModelYear, newListPrice);
            System.out.println("Product information after update: " + product);
        }
    }
//    hàm xóa sản phẩm
    public void deleteProduct(){
        String id = Inputter.getString("Enter the id to delete: ", "Must follow PXXX format", "^P\\d{3}$");
        Product product = productDAO.deletePro(id);
        if(product == null){
            System.out.println("ID is not in the list");
        }else{
//            trả ra sp vừa xóa được cho ng dùng biết đây là sp gì 
            System.out.println("Deleted successfully!!" + product);
        }
    }
    
//  sắp xếp thứ tự theo giá
    public void printFromFile(){
//        sử dụng hàm load để đọc qua từng thằng và trả lại vào mảng result
        List<Product> result = productDAO.loadProductFromFile(PRODUCT_FILE);
        result.sort(Comparator.comparingInt(Product::getListPrice));
        for (Product item : result) {
            System.out.printf("%s, %s, %s, %s, %d, %d\n", item.getProID(), item.getProName(), 
                    braDAO.getBrandByID(item.getBrandID()).getBrandName(),
                    cateDAO.getCateByID(item.getCategoryID()).getCategoryName(),
                    item.getModelYear(), item.getListPrice());
        }
    }
    
//    tạo thêm menu lun cho gọn hàm main
    public void menu(){
        productDAO.setList(productDAO.loadProductFromFile(PRODUCT_FILE));
        // trước khi trương trình chạy mình load file vô cho có sẵn dữ liêụ
        while(true){
            int choice = Inputter.getMenu("*****MANAGEMENT BIKE*****\n" 
                    + "1. Add product\n"
                    + "2. Search product by product name\n"
                    + "3. Update product\n"
                    + "4. Delete product\n"
                    + "5. Save product to file\n"
                    + "6. Print list product from file\n"
                    + "7. Search product by brandid\n"
                    + "8. Search product by price\n"
                    + "9. Quit\n"
                    + "Enter ur choice: ",
                    "Must be 1 - 9", "Invalid", 1, 9);
            switch(choice){
                case 1:
                    addProduct();
                    break;
                case 2:
                    searchProByName();
                    break;
                case 3:
                    updateProduct();
                    break;             
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    productDAO.saveProductsToFile(productDAO.getListPro(), PRODUCT_FILE);
                    break;
                case 6:
                    printFromFile();
                    break;
                case 8:
                    searchProByBrandID();
                    break;
                case 9:
                    searchProByPrice(); 
                break;
                default:
                    System.exit(0);
            }
        }
    }

}
