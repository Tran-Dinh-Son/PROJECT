package RAM;
import java.io.Serializable;

public class RAMItem implements Serializable, Comparable {
   private static final long serialVersionUID = 1L; 
   private String type;
   private String code;
   private String bus;
   private int quantity;
   private String date;
   private String brand;
   private boolean isActive;


   public RAMItem() {
   }

   public RAMItem(String type, String code, String bus, int quantity, String date, String brand, boolean isActive) {
      this.type = type;
      this.code = code;
      this.bus = bus;
      this.quantity = quantity;
      this.date = date;
      this.brand = brand;
      this.isActive = true;
   }

   

   public String getBus() {
      return bus;
   }

   public void setBus(String bus) {
      this.bus = bus;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

  

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public String getDate() {
      return this.date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public String getBrand() {
      return this.brand;
   }

   public void setBrand(String brand) {
      this.brand = brand;
   }

   public boolean isIsActive() {
      return this.isActive;
   }

   public void setIsActive(boolean isActive) {
      this.isActive = isActive;
   }

   @Override
   public String toString() {
      System.out.println("-------------------------------------------------------------------------------------------");
      System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |\n", "Code", "Type","Bus","Quantity", "Date", "Brand");
      String str = String.format("| %-12s | %-12s | %-12s | %-12d | %-12s | %-12s |", this.getCode(),this.getType(),this.getBus(), this.getQuantity(), this.getDate(), this.getBrand());
      return str;
   }



   @Override
   public int compareTo(Object o) {
      if (o instanceof RAMItem) {
         RAMItem other = (RAMItem) o;
         return this.code.compareTo(other.code); 
     }
     return 0;
   }

}

