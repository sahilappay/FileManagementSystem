import config.Initializer;
import service.impl.DI;
import service.inter.FileManagerServiceInter;
import service.inter.MenuServiceInter;
import service.inter.UserServiceInter;

public class Main {

    private static MenuServiceInter menuService = DI.menuService();
    private static UserServiceInter userService = DI.userService();
    private static FileManagerServiceInter fileManagerService = DI.fileManagerService();

    public static void main(String[] args) throws Exception{
       Initializer.initialize();
       int selectedMenu = menuService.showMenu();
       while (true){
           if(selectedMenu==1){
               userService.printAllUsers();
           }else if(selectedMenu==2){
               userService.printAllActiveUsers();
           }else if(selectedMenu==3){
               userService.printAllInActiveUsers();
           }else if(selectedMenu==4){
               userService.approveUser();
           }else if(selectedMenu==5){
               userService.printMySelf();
           }else if(selectedMenu==6){
               fileManagerService.printAllSubFoldersOrFileName();
           }else if(selectedMenu==7){
               userService.register();
           }else if(selectedMenu==8){
               userService.login();
           }
       }
    }
}
