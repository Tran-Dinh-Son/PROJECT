package data;
public class Product {
//    props
    private String proID;
    private String proName;
    private String brandID;
    private String categoryID;
    private int modelYear;
    private int listPrice;
    
//    constructor

    public Product(String proID, String proName, String brandID, String categoryID, int modelYear, int listPrice) {
        this.proID = proID;
        this.proName = proName;
        this.brandID = brandID;
        this.categoryID = categoryID;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

    public Product() {
    }
    
//    getter

    public String getProID() {
        return proID;
    }

    public String getProName() {
        return proName;
    }

    public String getBrandID() {
        return brandID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public int getModelYear() {
        return modelYear;
    }

    public int getListPrice() {
        return listPrice;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

   
    
    
//    setter

    

    @Override
    public String toString() {
        String str = String.format("%s, %s, %s, %s, %d, %d", 
                proID, proName, brandID, categoryID, modelYear, listPrice);
        return str;
    }

    
    
}
