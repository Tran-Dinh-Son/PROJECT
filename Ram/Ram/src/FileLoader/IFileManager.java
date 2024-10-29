package FileLoader;

import java.util.List;

public interface IFileManager<E> {

    boolean savefile(List<E> ramList); 
    List<E> readfile();
}
