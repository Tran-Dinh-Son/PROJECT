package data;
public class Category {
//    props
    private String categoryID;
    private String categoryName;

//    constructor
    public Category(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }
    
//    getter
    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }
    
//    setter
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
//    các method khác
    @Override
    public String toString() {
        String str = String.format("%s, %s", categoryID, categoryName);
        return str;
    }
    
}
