package dao;

import data.Product;
import java.util.List;

public interface ProductDAO {
//    hàm thêm vào product
//    nếu thêm thành công sẽ trả về true và ngược lại 
    boolean addProduct(Product product);
//    hàm lấy sản phẩm ra ID và trả ra sản phẩm lấy đc
    Product getProductByID(String ID);
//    hàm trả ra tất cả sản phẩm có trong danh sách 
    List<Product> getListPro();
    void setList(List<Product> list);
//    hàm tim kiếm sản phẩm by name
    List<Product> searchProByName(String name);
    
//    hàm cập nhật thông tin sản phẩm (trừ id k đc update theo đề cho)
    void updatePro(Product product ,String newName, String newBrandID, 
            String newCategoryID, int newModelYear, int newListPrice);
//    hàm xóa sản phẩm theo id và trả ra sản phẩm vừa xóa
    Product deletePro(String ID);
//    hàm lưu sản phẩm vô trong file
    void saveProductsToFile(List<Product> product, String fileName);
// Tải danh sách sản phẩm từ file
    List<Product> loadProductFromFile(String fileName);
// In ra danh sách sản phẩm

    public List<Product> searchProByBrandID(String brandID);

    public List<Product> searchProByPrice(double minPrice, double maxPrice);
    
}
