import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static final String PATH_FOLDER_GAMES = "F://Java//Games";

    public static void main(String[] args) {
        File folderGames = new File(PATH_FOLDER_GAMES);
        StringBuilder strLog = new StringBuilder("");

        if (folderGames.mkdir()) {
            strLog.append("Директория успешно создана: " + PATH_FOLDER_GAMES);
        }

        newFolders(new File(PATH_FOLDER_GAMES + "//src"), strLog);
        newFolders(new File(PATH_FOLDER_GAMES + "//res"), strLog);
        newFolders(new File(PATH_FOLDER_GAMES + "//savegames"), strLog);
        newFolders(new File(PATH_FOLDER_GAMES + "//temp"), strLog);

        File folderSrc = new File(PATH_FOLDER_GAMES + "//src");
        newFolders(new File(folderSrc.toString() + "//main"), strLog);
        newFolders(new File(folderSrc.toString() + "//test"), strLog);

        File folderMain = new File(folderSrc.toString() + "//main");
        newFiles(new File(folderMain.toString() + "//Main.java"), strLog);
        newFiles(new File(folderMain.toString() + "//Utils.java"), strLog);

        File folderRes = new File(PATH_FOLDER_GAMES + "//res");
        newFolders(new File(folderRes.toString() + "//drawables"), strLog);
        newFolders(new File(folderRes.toString() + "//vectors"), strLog);
        newFolders(new File(folderRes.toString() + "//icons"), strLog);

        //создаем файл темп и записываем в него лог
        File fileTemp = new File(PATH_FOLDER_GAMES + "//temp//temp.txt");
        newFileTemp(fileTemp, strLog);

        msg("Работа программы завершена.");
    }


    public static void newFolders(File folder, StringBuilder strLog) {
        if (folder.mkdir()) {
            strLog.append(" \nДиректория успешно создана: " + folder.toString());
        }
    }

    public static void newFileTemp(File file, StringBuilder strLog) {
        String txt = strLog.toString();
        byte[] bytes = txt.getBytes();
        try (FileOutputStream fos = new FileOutputStream(file, false);
                BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(bytes, 0, bytes.length);
        } catch (IOException ex) {
            msg(ex.getMessage());
        }
    }

    public static void newFiles(File file, StringBuilder strLog) {
        try {
            if (file.createNewFile()) {
                strLog.append(" \nФайл успешно создан: " + file.toString());
            }
        } catch (IOException ex) {
            msg(ex.getMessage());
        }
    }

    public static void msg(String str) {
        System.out.println(str);
    }
}
