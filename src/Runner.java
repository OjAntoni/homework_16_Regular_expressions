import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        String[] Userdata = UserMeeter.meet();
        Folder folder = null;
        Reader reader = null;
        try {
            folder = new Folder(Userdata[0], Integer.parseInt(Userdata[1]));
            reader = new Reader(folder.getPathNames());
            for(Map.Entry<String,Document> entry : reader.getFinalData().entrySet()){
                System.out.println(entry.getKey() +"  :  "+ entry.getValue());
            }
            System.out.println("------------------------------------------------------");
            System.out.println("Неподходящих файлов в папке: "+ folder.getInvalidCount());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            assert folder != null;
                folder.closeAll();
            assert reader != null;
                reader.closeAll();
        }
    }
}
