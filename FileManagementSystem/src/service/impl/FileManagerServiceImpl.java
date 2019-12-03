package service.impl;

import bean.Config;
import bean.User;
import config.Initializer;
import service.inter.UserServiceInter;
import util.ReadFileIO;
import util.WriteToFileIO;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public final class FileManagerServiceImpl extends AbstractFileManager {

    protected FileManagerServiceImpl() {

    }

    private UserServiceInter userServiceInter = DI.userService();

    @Override
    public boolean checkUserAccessToFile(String fileOrFolderPath) {
        User user = userServiceInter.getLoggedInUser();
        List<String> nonAccessibleFileOrFolders = user.getNonAccessableFilesOrFolders();
        boolean hasAccess = !nonAccessibleFileOrFolders.contains(fileOrFolderPath);
        return hasAccess;
    }


    @Override
    public String getSelectedFileOrFolderPath() {
        //Mushteriden Scanner vasitesile file ve ya folder adi teleb olunacaq ve return olunacaq
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert file or folder name");
        String folderOrFileName = scanner.nextLine();
        return folderOrFileName;
    }

    @Override
    public boolean isItFolder(String path) {
        //burada path eger fodlerdirse return true eks halda false qaytarirsiniz.
        Path file = new File(path).toPath();
        boolean exists = Files.exists(file);        // Check if the file exists
        boolean isDirectory = Files.isDirectory(file);   // Check if it's a directory
        boolean isFile = Files.isRegularFile(file); // Check if it's a regular file
        if (exists)
            if (isDirectory) {
                return true;
            } else {
                return false;
            }
        return false;
    }

    @Override
    public void printAllSubFilesAndFolders(String folderPath) {
        //bu methoda gonderilen folder path-in ichinde olan butun folder ve file adlarini alt-alta printe versin
        Path file = new File(folderPath).toPath();
        boolean exists = Files.exists(file);        // Check if the file exists
        boolean isDirectory = Files.isDirectory(file);   // Check if it's a directory
        if(exists){
            System.out.println(isDirectory);
            System.out.println("ls"+"%d");
        }
       // boolean isFile = Files.isRegularFile(file); // Check if it's a regular file
    }

    @Override
    public Config readConfig() {
        //Config obyektini filedan oxumaq lazimdir.
        return (Config) ReadFileIO.readFileDeserialize(Config.fileName);
    }

    @Override
    public boolean refreshConfig() {
        return WriteToFileIO.writeObjectToFile(Initializer.config, Config.fileName);
    }

}
