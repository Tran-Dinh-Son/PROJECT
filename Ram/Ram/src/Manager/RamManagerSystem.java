package Manager;

import java.util.List;
import java.util.Comparator;
import RAMDAO.IRAMDAO;
import RAMDAO.RAMDAOImpl;
import FileLoader.FileManager;
import RAM.RAMItem;
import Inptter.Inputted;
import Menu.Menu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RamManagerSystem implements IRamManagerSystem{
     
    private IRAMDAO ramdao;

    public RamManagerSystem() {
        this.ramdao = new RAMDAOImpl(); 
    }
    

   
   @Override
    public void AddRam() {
    String type = Inputted.GetString("Enter RAM type: ");
    int quantity = Inputted.GetInt("Enter RAM quantity: ");
    int busSpeed = Inputted.GetInt("Enter RAM bus speed (number only): ");
    String bus = busSpeed + "MHz";
    String brand = Inputted.GetString("Enter RAM brand: ");
    
    String date;
    while (true) {
        date = Inputted.GetString("Enter Production Date (DD/MM/YYYY): ");
        if (isValidDate(date)) {
            break; // Nếu ngày hợp lệ, thoát khỏi vòng lặp
        } else {
            System.out.println("Invalid date. Please enter a date from October 2024 or earlier.");
        }
    }

    String code = ramdao.generateCode(type);
    RAMItem ram = new RAMItem(type.toUpperCase(), code, bus, quantity, date, brand.toUpperCase(), true);
    
    if (ramdao.findCode(code) != null) {
        System.out.println("RAM item with code " + code + " already exists.");
        return; 
    }
    
    if (ramdao.addRam(ram)) {
        System.out.println("RAM item added successfully!");
    } else {
        System.out.println("Failed to add RAM item.");
    }
    }
    
    private boolean isValidDate(String date) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    try {
        LocalDate inputDate = LocalDate.parse(date, formatter);
        LocalDate cutoffDate = LocalDate.of(2024, 10, 30); // Ngày cắt là 01/10/2024
        return !inputDate.isAfter(cutoffDate); // Kiểm tra xem ngày nhập vào có sau ngày cắt không
    } catch (DateTimeParseException e) {
        return false; // Nếu không thể phân tích ngày, trả về false
    }
    }  
    
    @Override
    public void ReadRamFile() {
        FileManager f = new FileManager();
        List<RAMItem> rams = f.readfile();
        for (RAMItem ramItem : rams) {
            ramdao.addRam(ramItem);
        }

    }

    @Override
    public void WriteRamFile() {
        FileManager f = new FileManager();
        f.savefile(ramdao.getAllRAMItem());
    }

    @Override
    public void SearchRam() {
        Menu<String> menu = new Menu<>();
       menu.add("search by type");
       menu.add("search by bus");
       menu.add("search by brand");
       menu.add("search by date");
       menu.add("Exit");
      
        boolean exist = false;
        while (!exist) {
            menu.showMenu();
            int chocie = menu.getChoice();

            switch (chocie) {
                case 1:
                    String type = Inputted.GetString("Enter type you want search: ");
                    List<RAMItem> ramtype = ramdao.searchbytype(type);
                    if(!ramtype.isEmpty()){
                       for (RAMItem ram : ramtype) {
                            System.out.println(ram);
                       }
                    }else {
                        System.out.println("No RAM found with type '" + type + "'.");
                    }
                    break;
                case 2:
                    String bus = Inputted.GetString("Enter bus you want search: ");
                    List<RAMItem> rambus = ramdao.searchbybus(bus);
                    if(!rambus.isEmpty()){
                        for (RAMItem ram : rambus) {
                             System.out.println(ram);
                        }
                     }else {
                         System.out.println("No RAM found with bus '" + bus + "'.");
                     }
                     break;
                case 3:
                    String brand = Inputted.GetString("Enter brand you want search: ");
                    List<RAMItem> rambrand = ramdao.searchbybrand(brand);
                    if (rambrand != null && !rambrand.isEmpty()) {
                        System.out.println("Results for brand '" + brand + "':");
                        for (RAMItem ram : rambrand) {
                            System.out.println(ram);
                        }
                    } else {
                        System.out.println("No RAM found with brand '" + brand + "'.");
                    }
                    break;
                case 4: 
                String date = Inputted.GetString("Enter Production Date (DD/MM/YYYY): ");
                List<RAMItem> foundRams = ramdao.searchByDate(date); // Gọi phương thức searchByDate
                if (foundRams != null && !foundRams.isEmpty()) {
                    System.out.println("Results for date '" + date + "':");
                    for (RAMItem ram : foundRams) {
                        System.out.println(ram);
                    }
                } else {
                    System.out.println("No RAM found with production date '" + date + "'.");
                }
                break;
                case 5:
                    System.out.println("Exiting search menu.");
                    exist = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }
        }
        
    }
    

    @Override
    public void UpdateRam() {
        String code = Inputted.NewString("Enter code RAM to update: ", "");
        RAMItem exist = ramdao.findCode(code);
        if (exist == null) {
            System.out.println("the ram not exist");
            return;
        }
    
        String newtype = Inputted.NewString("Enter ram type: ",exist.getType());
        int newquantity = Inputted.GetNewInt("Enter ram quantity",exist.getQuantity());
        String newbusSpeed = Inputted.NewString("Enter RAM bus speed (number only) ",exist.getBus());
        String newbus = newbusSpeed + "MHz";
        String newbrand = Inputted.NewString("Enter ram brand: ",exist.getBrand());
        String newdate = Inputted.NewString("Enter Enter Production Date (DD/MM/YYYY)",exist.getDate());
        code = ramdao.generateCode(newtype);
        if(!code.isEmpty()) ramdao.findCode(code);
        exist.setCode(code);
        exist.setType(newtype.toUpperCase());
        exist.setQuantity(newquantity);
        exist.setBus(newbus);
        exist.setBrand(newbrand.toUpperCase());
        exist.setDate(newdate);
        ramdao.updateRam(exist);
        System.out.println("RAM updated successfully.");

       
    }

   @Override
    public void DeleteRam() {
    Menu<String> menu = new Menu<>();
    menu.add("Delete by Type");
    menu.add("Delete by Bus");
    menu.add("Delete by Brand");
    menu.add("Delete by Date");
    menu.add("Delete by Code");
    menu.add("Delete All");
    menu.add("Exit");

    boolean exist = false;
    while (!exist) {
        menu.showMenu();
        int choice = menu.getChoice();

        switch (choice) {
            case 1:
                String type = Inputted.GetString("Enter RAM type to delete: ");
                deleteByType(type);
                break;
            case 2:
                String bus = Inputted.GetString("Enter RAM bus to delete: ");
                deleteByBus(bus);
                break;
            case 3:
                String brand = Inputted.GetString("Enter RAM brand to delete: ");
                deleteByBrand(brand);
                break;
            case 4:
                String date;
                while (true) {
                    date = Inputted.GetString("Enter Production Date (DD/MM/YYYY) to delete: ");
                    if (isValidDate(date)) {
                        break; // Nếu ngày hợp lệ, thoát khỏi vòng lặp
                    } else {
                        System.out.println("Invalid date. Please enter a date from October 2024 or earlier.");
                    }
                }
                deleteByDate(date);
                break;
            case 5:
                String code = Inputted.GetString("Enter RAM code to delete: ");
                deleteByCode(code);
                break;
            case 6:
                deleteAll();
                break;
            case 7:
                System.out.println("Exiting delete menu.");
                exist = true;
                break;
            default:
                System.out.println("Invalid choice. Please select again.");
                break;
        }

//        // In thông tin RAM đã đánh dấu là xóa
//        System.out.println("-------------------------------------------------------------------------------------------");
//        System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |\n", "Code", "Type", "Bus", "Quantity", "Date", "Brand");
//        System.out.println("-------------------------------------------------------------------------------------------");
//        System.out.printf("| %-12s | %-12s | %-12s | %-12d | %-12s | %-12s |\n", 
//                          ramToDelete.getCode(), ramToDelete.getType(), ramToDelete.getBus(), ramToDelete.getQuantity(), ramToDelete.getDate(), ramToDelete.getBrand());
//        System.out.println("-------------------------------------------------------------------------------------------");
//    } else {
//        System.out.println("RAM deletion canceled.");
  }
}
private void deleteByType(String type) {
    ramdao.deleteByType(type); // Gọi phương thức deleteByType từ RAMDAOImpl
}

private void deleteByBus(String bus) {
    ramdao.deleteByBus(bus); // Gọi phương thức deleteByBus từ RAMDAOImpl
}

private void deleteByBrand(String brand) {
    ramdao.deleteByBrand(brand); // Gọi phương thức deleteByBrand từ RAMDAOImpl
}

private void deleteByDate(String date) {
    ramdao.deleteByDate(date); // Gọi phương thức deleteByDate từ RAMDAOImpl
}

private void deleteByCode(String code) {
    ramdao.deleteByCode(code); // Gọi phương thức deleteByCode từ RAMDAOImpl
}

private void deleteAll() {
    ramdao.deleteAll(); // Gọi phương thức deleteAll từ RAMDAOImpl
}
    
  @Override
    public void DisplayDeletedRamItems() {
    FileManager f = new FileManager();
    List<RAMItem> deletedItems = f.readfile(); // Giả sử bạn có phương thức này trong FileManager

    // Nếu danh sách trống hoặc null
    if (deletedItems == null || deletedItems.isEmpty()) {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |\n", "Code", "Type", "Bus", "Quantity", "Date", "Brand");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("|                            No deleted RAM found.                          |");
        System.out.println("-------------------------------------------------------------------------------------------");
        return;
    }

    // In các RAM đã bị xóa
    System.out.println("===========================================================================================");
    System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |\n", "Code", "Type", "Bus", "Quantity", "Date", "Brand");
    System.out.println("===========================================================================================");

    boolean hasDeletedItems = false; // Cờ kiểm tra xem có mục nào bị xóa không
    for (RAMItem ram : deletedItems) {
        if (!ram.isIsActive()) { // Kiểm tra nếu RAM đã bị xóa (không hoạt động)
            System.out.printf("| %-12s | %-12s | %-12s | %-12d | %-12s | %-12s |\n",
                    ram.getCode(), ram.getType(), ram.getBus(), ram.getQuantity(), ram.getDate(), ram.getBrand());
            hasDeletedItems = true;
        }
    }

    // Nếu không có RAM nào bị xóa
    if (!hasDeletedItems) {
        System.out.println("|                             No deleted RAM found..                           |");
    }

    System.out.println("============================================================================================");
}

   
   

    @Override
    public void PrintAllRamItem() {
        List<RAMItem> list = ramdao.getAllRAMItem();

        if (list == null || list.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
//        list.sort(Comparator.comparing(RAMItem::getType).reversed()
//            .thenComparing(RAMItem::getDate));

    
        Menu<String> menu = new Menu<>();
        menu.add("Sort by Type");
        menu.add("Sort by Brand");
        menu.add("Sort by Bus");
        menu.add("Print without Sorting");
        menu.add("Quit");
        boolean exit = false;
        while (!exit) {
            menu.showMenu();
            int choice = menu.getChoice();
    
            switch (choice) {
                case 1:
                    sortByType(list); 
                    System.out.println("Sorted by Type:");
                    break;
                case 2:
                    sortByBrand(list); 
                    System.out.println("Sorted by Brand:");
                    break;
                case 3:
                    sortByBus(list); 
                    System.out.println("Sorted by Bus:");
                    break;
                case 4:
                    System.out.println("Print without Sorting:");
                    break;
                case 5:
                    System.out.println("Exiting the print menu.");
                    exit = true;
                    continue;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }
    
            System.out.println("==========================================================================================================");
            System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |\n", "Code", "Type", "Bus", "Quantity", "Date", "Brand","IsActive");
            System.out.println("==========================================================================================================");
    
          for (RAMItem ram : list) {
    if (ram.isIsActive()) {
        String warning = "";  // Biến để lưu thông báo cảnh báo
        if (ram.getQuantity() < 4) {
            warning = " (warning)";  // Thông báo nếu số lượng nhỏ hơn 4
        }
        // In thông tin RAM, thêm cảnh báo nếu có
        System.out.printf("| %-12s | %-12s | %-12s | %-12d | %-12s | %-12s | %-12s |\n", 
                          ram.getCode(), ram.getType(), ram.getBus(), 
                          ram.getQuantity(), ram.getDate(), ram.getBrand(), warning);
    }
}
    
            System.out.println("==========================================================================================================");
        }
   }
    private void sortByType(List<RAMItem> list) {
    list.sort(Comparator.comparing(RAMItem::getType));
    }

    private void sortByBrand(List<RAMItem> list) {
        list.sort(Comparator.comparing(RAMItem::getBrand));
    }

    private void sortByBus(List<RAMItem> list) {
        list.sort(Comparator.comparing(RAMItem::getBus));
    }
}
