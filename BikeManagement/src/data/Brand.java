package data;
public class Brand {
//    props  
    private String brandID;
    private String brandName;
    private String brandCountry;
    
//    constructor
    public Brand(String brandID, String brandName, String brandCountry) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandCountry = brandCountry;
    }

    public Brand() {
    }
    
//    getter
    public String getBrandID() {
        return brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getBrandCountry() {
        return brandCountry;
    }
    
//    setter
    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setBrandCountry(String brandCountry) {
        this.brandCountry = brandCountry;
    }
    
//    các method khác
    @Override
    public String toString() {
        String str = String.format("%s, %s, %s", 
                    brandID, brandName, brandCountry);
        return str;
    }

    public String getCountry() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
