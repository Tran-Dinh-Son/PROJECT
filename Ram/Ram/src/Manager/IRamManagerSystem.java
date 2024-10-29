package Manager;

import java.util.List;
import RAM.RAMItem;


public interface IRamManagerSystem {

    void AddRam();
    void UpdateRam();
    void DeleteRam();
    void SearchRam();
    void PrintAllRamItem(); 
    void ReadRamFile();
    void WriteRamFile();
    void DisplayDeletedRamItems();
    
}
