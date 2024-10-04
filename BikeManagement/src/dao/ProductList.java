package dao;

import FileLoader.FileLoader;
import data.Brand;
import data.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import util.Inputter;   

public class ProductList implements ProductDAO{
//    props
    private List<Product> listPro;
    private String lastID;
//    constructor

    public ProductList() {
        listPro = new ArrayList<>();
        lastID = "";
    }
//thêm một sản phẩm mới vào danh sách
    @Override
    public boolean addProduct(Product product){
// Nếu danh sách trống thì khởi tạo với ID P001
    if (listPro.isEmpty()) {
            lastID = "P001";
        } else {
            String lastIDProduct = listPro.get(listPro.size() - 1).getProID();
            int number = Integer.parseInt(lastIDProduct.substring(1));
            lastID = String.format("P%03d", number + 1);
        }
    // Gán ID mới cho đối tượng sản phẩm
    product.setProID(lastID);

    // Thêm sản phẩm mới vào danh sách
    listPro.add(product);
    return true;
    }
//so sánh mã ID của sản phẩm
    @Override
    public Product getProductByID(String ID) {
        for (Product item : listPro) {
            if(item.getProID().equalsIgnoreCase(ID)){// không phân biệt chữ hoa hay chữ thường
                return item;
            }
        }
        return null;//không tìm thấy sản phẩm nào có mã ID khớp trả null
    }

    @Override
    public List<Product> getListPro() {
        return listPro; //trả về danh sách tất cả các sản phẩm listPro
    }

    @Override
    public void setList(List<Product> list) {
        this.listPro = list; 
        // nhận vào một danh sách sản phẩm list và gán nó cho biến listPro
    }

    @Override
    public List<Product> searchProByName(String name) {
        List<Product> result = new ArrayList<>();// Tạo một danh sách rỗng result để chứa các sản phẩm tìm thấy.
        for (Product item : listPro) { // dòng fore duyệt danh sách proList
            if(item.getProName().toLowerCase().contains(name.toLowerCase())){//Lấy tên sản phẩm chuyển đổi để so sánh không phân biệt hoa thường
                result.add(item);//sản phẩm thỏa mãn điều kiện, nó sẽ được thêm vào danh sách
            }
        }
        return result; // trả ra cái mảng mới chứa đc name mình search 
    }

    @Override
    public void updatePro(Product product, String newName, 
            String newBrandID, String newCategoryID,
            int newModelYear, int newListPrice) {
//        nếu để trông thì dùng thông tin cũ
        if(!newName.isEmpty()){// Nếu newName không trống, cập nhật tên sản phẩm bằng setProName
            product.setProName(newName);
        }if(!newBrandID.isEmpty()){//Nếu newBrandID không trống, cập nhật ID thương hiệu của sản phẩm.
            product.setBrandID(newBrandID);
        }if(!newCategoryID.isEmpty()){
            product.setCategoryID(newCategoryID);
        }if(newModelYear >= 1000 && newModelYear <= 2024){ //không vượt quá năm hiện tại
            product.setModelYear(newModelYear);
        }if(newListPrice > 0){// giá thì phải cao hơn 0 
            product.setListPrice(newListPrice);
        }
    }

    @Override
    public Product deletePro(String ID) {
        for(int i = 0; i <= listPro.size() - 1; i++){
            if(listPro.get(i).getProID().equalsIgnoreCase(ID)){
                return listPro.remove(i); // trả về sản phẩm bị xóa cho người dùng
            }
        }
        return null; // k thấy sản phẩm cần xóa hoặc không có trong danh sách
    }

    @Override
    public void saveProductsToFile(List<Product> product, String fileName) {  
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for (Product item : product) {
                writer.write(item.getProID() + "," + item.getProName() + "," + 
                        item.getBrandID() + "," + item.getCategoryID() + "," + 
                        item.getModelYear() + "," + item.getListPrice());
//                uyệt qua danh sách sản phẩm ghi từng thuộc tính của sản phẩm vào file giá trị phân cách bằng dấu phẩy
                writer.newLine();// viết xong mình xuống dòng cho dễ nhìn 
            }
           
        }catch(Exception e){
            System.out.println("Writing error: " + e);
        }
    }

    @Override
    public  List<Product> loadProductFromFile(String fileName){
        listPro = FileLoader.loadProductFromFile(fileName);
        return listPro;
    }

    @Override
public List<Product> searchProByBrandID(String brandID) {
    List<Product> result = new ArrayList<>(); // Khởi tạo danh sách rỗng
    for (Product product : listPro) { // Duyệt qua danh sách sản phẩm
        if (product.getBrandID().equalsIgnoreCase(brandID)) { // Kiểm tra brandID
            result.add(product); // Thêm sản phẩm vào kết quả
        }
    }
    return result; // Trả về danh sách sản phẩm tìm thấy
}

@Override
public List<Product> searchProByPrice(double minPrice, double maxPrice) {
    List<Product> result = new ArrayList<>(); // Khởi tạo danh sách rỗng
    for (Product product : listPro) { // Duyệt qua danh sách sản phẩm
        if (product.getListPrice() >= minPrice && product.getListPrice() <= maxPrice) { // Kiểm tra giá
            result.add(product); // Thêm sản phẩm vào kết quả
        }
    }
    return result; // Trả về danh sách sản phẩm tìm thấy
}
}
   
    

    

    