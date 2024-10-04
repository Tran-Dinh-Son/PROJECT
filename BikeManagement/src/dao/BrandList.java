package dao;
//BrandList dùng để quản lý vào làm chức năng cho brand

import FileLoader.FileLoader;
import data.Brand;
import data.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BrandList implements BrandDAO{
//    props
    private List<Brand> listBrand;
    private static final String BRAND_FILE = "Brand.txt";
    private List<Product> productList;

//    constructor
    public BrandList() {
//        khởi tạo ra khu vực lưu trữ và đọc dữ liệu 
        listBrand = new ArrayList<>(); // làm cho brand 1 danh sách rỗng để lưu vào
        loadBrandFromFile(BRAND_FILE);
//  BRAND_FILE là một hằng số đại diện cho tên của file 
//  Phương thức này sẽ đọc dữ liệu từ file và nạp  vào danh sách listBrand.
    }
    
      
//    hàm in từ file brand
    
    @Override
    public Brand getBrandByID(String brandID) {
//      hàm này phục vụ cho việc lấy brandID từ trong file ra xhem có hong
        for (Brand item : listBrand) {
            if(item.getBrandID().equalsIgnoreCase(brandID)){
                return item; // nếu tìm thấy thì trả về đối tượng
            }
        }
        return null; // còn k trả null ch có
    }

    @Override
//    hàm nhận vào file và đọc danh sách chứa các đối tượng brand và trả lại danh sách
    public List<Brand> loadBrandFromFile(String fileName) {
        listBrand = FileLoader.loadBrandFromFile(fileName); // Use the FileLoader
        return listBrand;
    }

    @Override
    public void printFile() {
        for (Brand item : listBrand) {
            System.out.println(item);
        }
    }
    
    

    @Override
    public void saveBrandToFile(String fileName) {
        FileLoader.saveBrandToFile(listBrand, fileName); //To change body of generated methods, choose Tools | Templates.
    }
  }

    

