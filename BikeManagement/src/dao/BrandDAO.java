package dao;

import data.Brand;
import data.Product;
import java.util.List;

public interface BrandDAO {
//    hàm cũng như cate phục vụ cho function 6
    Brand getBrandByID(String brandID);
//    đọc dữ liệu từ file trả ra danh sách
    List<Brand> loadBrandFromFile(String fileName);
//    print
    void printFile();
// Hàm lưu danh sách Brand vào file
    void saveBrandToFile(String fileName);
}
