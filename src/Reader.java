import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    private final List<BufferedReader> files;
    private  Map<String, Document> finalData = new HashMap<>();

    public Reader(List<BufferedReader> bfList) {
        files = bfList;
        read();
    }

    /**
     * Метод служит для чтения файлов в каталоге и нахождение в каждом из файлов
     * еужной нам информации
     */
    public void read() {
        for (BufferedReader file : files){
            String str;
            Pattern docP = Pattern.compile("\\d{4}[-][a-zа-я]{3}[-]\\d{4}[-][a-zа-я]{3}[-]\\d[a-zа-я]\\d[a-zа-я]", Pattern.CASE_INSENSITIVE);
            Pattern phoneP = Pattern.compile("(\\+*)[(]\\d{2}[)]\\d{7}([\\W\\n\\t]|$)");
            Pattern emailP = Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE);
            while (true) {
                try {
                    if ((str = file.readLine()) == null) break;
                    else{
                        Matcher docMatcher = docP.matcher(str);
                        Matcher phoneMatcher = phoneP.matcher(str);
                        Matcher emailMatcher = emailP.matcher(str);
                        if(docMatcher.find()){
                            String doc = str.substring(docMatcher.start(),docMatcher.end());
                            String phone = "No number";
                            String email = "No email";
                            if(phoneMatcher.find())
                                phone =  str.substring(phoneMatcher.start(), phoneMatcher.end());
                            if(emailMatcher.find())
                                email = str.substring(emailMatcher.start(), emailMatcher.end());
                            Document d = new Document(doc,phone,email);
                            finalData.put(d.getDocNum(),d);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void closeAll(){
        for(BufferedReader bf : files){
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        finalData.clear();
    }

    public Map<String, Document> getFinalData() {
        return finalData;
    }
}