package FileLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import RAM.RAMItem;

public class FileManager implements IFileManager<RAMItem>{

    private static final String FILE_NAME = "RAMlist.dat";


    @Override
    public boolean savefile(List<RAMItem> ramList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(ramList);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<RAMItem> readfile() {
        List<RAMItem> ramList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ramList = (List<RAMItem>) ois.readObject();
        } catch (FileNotFoundException e) {
           
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ramList;
    }
    
}
