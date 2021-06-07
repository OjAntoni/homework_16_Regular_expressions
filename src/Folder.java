import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, ответственный за основную работу с каталогом
 */
public class Folder {

    public static File folder;
    private List<BufferedReader> pathNames;
    private int invalidCount;

    public Folder(String folderPath, int maxFiles) throws Exception {
        folder = new File(folderPath);
        if(!folder.exists()){
            throw new Exception("Такой папки не найдено!");
        }
        if(folder.isDirectory()){
            File[] pathNames = folder.listFiles((dir, name) -> name.endsWith("txt"));
            Optional<File[]> opt = Optional.ofNullable(pathNames);
            if(opt.isEmpty()) throw new Exception("Your directory doesn't contain source files!");
            int maxFilesToRead = Math.min(Math.max(maxFiles, 0), pathNames.length);
            this.pathNames =
            Arrays.stream(pathNames).map((path)-> {
                try {
                    return new BufferedReader(new FileReader(path));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }).limit(maxFilesToRead).collect(Collectors.toList());
            invalidCount= (int) folder.listFiles().length - folder.listFiles((dir, name) -> name.endsWith("txt")).length;
        }
    }

    public void closeAll(){
        folder=null;
        for(BufferedReader bf : pathNames){
            try {
                bf.close();
            } catch (IOException ignored) {
            }
        }
        pathNames = null;
    }

    public List<BufferedReader> getPathNames() {
        return pathNames;
    }

    public int getInvalidCount() {
        return invalidCount;
    }

}
