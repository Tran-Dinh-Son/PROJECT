/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileLoader;

import data.Brand;
import data.Category;
import data.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FileLoader {

    public static List<Brand> loadBrandFromFile(String fileName) {
        List<Brand> listBrand = new ArrayList<>();//tạo một danh sách rỗng để lưu các đối tượng Brand sau khi đọc từ file
        File f = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {//để đảm bảo tài nguyên file sẽ tự động được đóng sau khi đọc
            String line = reader.readLine();
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                String id = st.nextToken().trim();
                String name = st.nextToken().trim();
                String country = st.nextToken().trim();///loại bỏ khoảng trắng ở đầu và cuối
                Brand nBrand = new Brand(id, name, country);
                listBrand.add(nBrand);
                line = reader.readLine();
            }
            
        } catch (Exception e) {
            System.out.println("Error reading file Brand: " + e);
        }
        return listBrand;
    }

    public static void saveBrandToFile(List<Brand> listBrand, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Brand item : listBrand) {
                writer.write(item.getBrandID() + "," + item.getBrandName() + "," + item.getCountry());
                writer.newLine();
            }
            
        } catch (Exception e) {
            System.out.println("Error writing file Brand: " + e);
        }
    }

    public static List<Category> loadCategoryFromFile(String fileName) {
        List<Category> listCategory = new ArrayList<>();
        File f = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line = reader.readLine();
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                String id = st.nextToken().trim();
                String name = st.nextToken().trim();
                Category nCategory = new Category(id, name);
                listCategory.add(nCategory);
                line = reader.readLine();
            }
            
        } catch (Exception e) {
            System.out.println("Error reading Category file: " + e);
        }
        return listCategory;
    }

    public static void saveCategoryToFile(List<Category> listCategory, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Category item : listCategory) {
                writer.write(item.getCategoryID() + "," + item.getCategoryName());
                writer.newLine();
            }
            
        } catch (Exception e) {
            System.out.println("Error writing Category file: " + e);
        }
    }

    public static List<Product> loadProductFromFile(String fileName) {
        List<Product> proList = new ArrayList<>();
        File f = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line = reader.readLine();
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                String id = st.nextToken().trim();
                String name = st.nextToken().trim();
                String brandID = st.nextToken().trim();
                String categoryID = st.nextToken().trim();
                int modelYear = Integer.parseInt(st.nextToken().trim());
                int listPrice = Integer.parseInt(st.nextToken().trim());
                Product nProduct = new Product(id, name, brandID, categoryID, modelYear, listPrice);
                proList.add(nProduct);
                line = reader.readLine();
            }
           
        } catch (Exception e) {
            System.out.println("Error reading Product file:" + e);
        }
        return proList;
    }
}
    
