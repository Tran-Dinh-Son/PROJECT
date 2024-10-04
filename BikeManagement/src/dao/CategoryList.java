package dao;

import FileLoader.FileLoader;
import data.Brand;
import data.Category;
import data.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CategoryList implements CategoryDAO{
//    props
    private List<Category> listCategory;
    private static final String CATEGORY_FILE = "Category.txt";
    
//    constructor
    public CategoryList() {
        listCategory = new ArrayList<>();
        loadCateFromFile(CATEGORY_FILE);
    }

    @Override
    public Category getCateByID(String cateID) {
        for (Category item : listCategory) {
            if(item.getCategoryID().equals(cateID)){
                return item;// nếu tìm thấy thì sẽ trả ra item
            }
        }
        return null; //còn k thì trả ra null
    }

    @Override
    public List<Category> loadCateFromFile(String fileName) {
        listCategory = FileLoader.loadCategoryFromFile(fileName); // Sử dụng FileLoader
        return listCategory;
    }

   @Override
    public void printFile() {
        for (Category item : listCategory) {
            System.out.println(item);
        }
    }

    @Override
    public void saveCategoryToFile(String fileName) {
        FileLoader.saveCategoryToFile(listCategory, fileName);
    }
}
