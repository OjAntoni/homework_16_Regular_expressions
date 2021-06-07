import java.io.IOException;
import java.util.Scanner;

public class UserMeeter {
    public static String[] meet(){
        while(true) {
            try {
                Scanner s = new Scanner(System.in);
                System.out.print("Введите путь к папке с файлами: ");
                String folderPath = s.nextLine();
                System.out.print("Введите верхнюю границу количества файлов для чтения: ");
                int fileNumbers = s.nextInt();

                return new String[]{folderPath, String.valueOf(fileNumbers)};
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
