package RAMDAO;

import java.util.ArrayList;
import java.util.List;
import FileLoader.FileManager;
import java.util.Comparator;

import RAM.RAMItem;
import java.util.Collections;

public class RAMDAOImpl implements IRAMDAO{

    private List<RAMItem> ramlist;


    private void loadFromFile() {
        FileManager fileManager = new FileManager();
        List<RAMItem> itemsFromFile = fileManager.readfile();
        if (itemsFromFile != null) {
            ramlist.addAll(itemsFromFile);
        }
    }

    public RAMDAOImpl() {
        this.ramlist = new ArrayList<>(); 
        loadFromFile();
    }

    @Override
    public String generateCode(String type) {
    List<RAMItem> list = this.searchbytype(type);
    int y = 1;

    if (list != null && !list.isEmpty()) {
        RAMItem lastRAM = list.get(0);

        for (RAMItem item : list) {
            if (item.getCode().compareTo(lastRAM.getCode()) > 0) {
                lastRAM = item;
            }
        }

        String lastCode = lastRAM.getCode();
        String[] parts = lastCode.split("_");
        y = Integer.parseInt(parts[1]) + 1;
    }

    return "RAM" + type.toUpperCase() + "_" + y;
}


   
 

    @Override
    public boolean addRam(RAMItem ram) {
        if (checkcode(ram.getCode())) {
            return false;
        }
        ramlist.add(ram);
        return true;
    }

    @Override
    public boolean updateRam(RAMItem ram) {
        for (int i = 0; i < ramlist.size(); i++) {
            if(ramlist.get(i).getCode().equalsIgnoreCase(ram.getCode())){
                ramlist.set(i, ram);
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteRam(RAMItem ram) {
        for (RAMItem item : ramlist) {
            if (item.getCode().equalsIgnoreCase(ram.getCode()) && item.isIsActive()) {
                item.setIsActive(false);
                return;
            }
        }
    }

    @Override
    public List<RAMItem> getAllRAMItem() {
//       return new ArrayList<>(ramlist);
    ramlist.sort(Comparator.comparing(RAMItem::getDate)
                .thenComparing(RAMItem::getQuantity)
                .thenComparing(RAMItem::getType, Comparator.reverseOrder())); 
     return ramlist;
    }
    


    @Override
    public boolean checkcode(String code) {
        return findCode(code) != null;
    }

    @Override
    public RAMItem findCode(String code){
        for (RAMItem ramItem : ramlist) {
            if(ramItem.getCode().equalsIgnoreCase(code)){
                return ramItem;
            }
       }
       return null;
    }

    @Override
    public void savefile() {
        FileManager f = new FileManager();
        f.savefile(ramlist);
    }
  
    @Override
    public List<RAMItem> searchbytype(String type) {
        List<RAMItem> result = new ArrayList<>();
        for (RAMItem ram : ramlist) {
            if (ram.getType().equalsIgnoreCase(type) && ram.isIsActive()) {
                result.add(ram);
            }
        }
        result.sort(Comparator.comparing(RAMItem::getType));
        return result;
    }

    @Override
    public List<RAMItem> searchbybus(String bus) {
        List<RAMItem> result = new ArrayList<>();
        for (RAMItem ram : ramlist) {
            String ramBusSpeed = ram.getBus().replaceAll("\\D", "");
            if (ramBusSpeed.equalsIgnoreCase(bus) && ram.isIsActive()) {
                result.add(ram);
            }
        }
        result.sort(Comparator.comparing(RAMItem::getBus));
        return result;
    }

    @Override
    public List<RAMItem> searchbybrand(String brand) {
        List<RAMItem> result = new ArrayList<>();
        for (RAMItem ram : ramlist) {
            if (ram.getBrand().equalsIgnoreCase(brand) && ram.isIsActive()) {
                result.add(ram);
            }
        }
        result.sort(Comparator.comparing(RAMItem::getBrand));
        return result;
    }

    @Override
    public void fileDelete(RAMItem ram) {
       if (ramlist.removeIf(item -> item.getCode().equalsIgnoreCase(ram.getCode()))) {
        // Nếu RAMItem được tìm thấy và xóa, lưu vào file
        List<RAMItem> deletedItems = new ArrayList<>();
        deletedItems.add(ram);
        FileManager f = new FileManager();
        f.savefile(deletedItems); // Lưu danh sách RAMItem đã xóa
    }
}
    
    @Override
public void deleteByBrand(String brand) {
    List<RAMItem> itemsToDelete = searchbybrand(brand); // Tìm kiếm RAM theo thương hiệu
    for (RAMItem ram : itemsToDelete) {
        ram.setIsActive(false); // Đánh dấu RAM là đã xóa
        updateRam(ram); // Cập nhật trạng thái RAM trong danh sách
        fileDelete(ram); // Lưu RAM đã bị xóa vào file
    }
}

@Override
public void deleteByDate(String date) {
    List<RAMItem> itemsToDelete = searchByDate(date); // Tìm kiếm RAM theo ngày
    for (RAMItem ram : itemsToDelete) {
        ram.setIsActive(false); // Đánh dấu RAM là đã xóa
        updateRam(ram); // Cập nhật trạng thái RAM trong danh sách
        fileDelete(ram); // Lưu RAM đã bị xóa vào file
    }
}

@Override
public void deleteByCode(String code) {
    RAMItem ramToDelete = findCode(code); // Tìm kiếm RAM theo mã
    if (ramToDelete != null) {
        ramToDelete.setIsActive(false); // Đánh dấu RAM là đã xóa
        updateRam(ramToDelete); // Cập nhật trạng thái RAM trong danh sách
        fileDelete(ramToDelete); // Lưu RAM đã bị xóa vào file
    } else {
        System.out.println("No RAM found with code " + code + ".");
    }
}

@Override
public void deleteAll() {
    List<RAMItem> allRams = getAllRAMItem(); // Lấy tất cả RAM
    for (RAMItem ram : allRams) {
        ram.setIsActive(false); // Đánh dấu RAM là đã xóa
        updateRam(ram); // Cập nhật trạng thái RAM trong danh sách
        fileDelete(ram); // Lưu RAM đã bị xóa vào file
    }
}

@Override
public void deleteByBus(String bus) {
    List<RAMItem> itemsToDelete = searchbybus(bus); // Tìm kiếm RAM theo bus
    for (RAMItem ram : itemsToDelete) {
        ram.setIsActive(false); // Đánh dấu RAM là đã xóa
        updateRam(ram); // Cập nhật trạng thái RAM trong danh sách
        fileDelete(ram); // Lưu RAM đã bị xóa vào file
    }
}

@Override
public void deleteByType(String type) {
    List<RAMItem> itemsToDelete = searchbytype(type); // Tìm kiếm RAM theo loại
    for (RAMItem ram : itemsToDelete) {
        ram.setIsActive(false); // Đánh dấu RAM là đã xóa
        updateRam(ram); // Cập nhật trạng thái RAM trong danh sách
        fileDelete(ram); // Lưu RAM đã bị xóa vào file
    }
}

   @Override
    public List<RAMItem> searchByDate(String date) {
    List<RAMItem> result = new ArrayList<>();
    for (RAMItem ram : ramlist) {
        if (ram.getDate().equals(date) && ram.isIsActive()) {
            result.add(ram);
        }
    }
    return result;
}
    

}
