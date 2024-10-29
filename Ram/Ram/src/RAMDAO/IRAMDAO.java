package RAMDAO;

import java.util.List;

import RAM.RAMItem;

public interface IRAMDAO {

    boolean addRam(RAMItem ram);
    String generateCode(String type);
    boolean updateRam(RAMItem ram);
    void deleteRam(RAMItem ram);
    List<RAMItem> searchbytype(String type);
    List<RAMItem> searchbybus(String bus);
    List<RAMItem> searchbybrand(String brand);
    List<RAMItem> searchByDate(String date);
    List<RAMItem> getAllRAMItem();
    boolean checkcode(String code);
    RAMItem findCode(String code);
    void savefile();
    void fileDelete(RAMItem ram);
    void deleteByBrand(String brand);
    void deleteByDate(String date);
    void deleteByCode(String code);
    void deleteAll();
    void deleteByBus(String bus);
    void deleteByType(String type);

   
}
