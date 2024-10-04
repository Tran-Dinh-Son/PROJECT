package dao;

import data.Category;
import java.util.List;

public interface CategoryDAO {
//    hàm phục vụ cho function 6 trả ra CateName
//    hàm nhận vào id và trả ra cate
    Category getCateByID(String ID);
//    đọc dữ liệu từ file txt cho sẵn và trả về chương trình
    List<Category> loadCateFromFile(String fileName);
// Lưu danh sách Category vào file    
    void saveCategoryToFile(String fileName);
//    print
    void printFile();
}
