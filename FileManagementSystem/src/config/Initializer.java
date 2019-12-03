package config;

import bean.Config;
import bean.User;
import service.impl.DI;
import service.inter.FileManagerServiceInter;
import java.util.Arrays;

public class Initializer {

    public static Config config;

    private static FileManagerServiceInter fms = DI.fileManagerService();

    public static void initialize() throws Exception{
        config = fms.readConfig();
        if(config==null){
            User user = new User("admin","admin");
            user.setStatus(1);//Active
            user.setPosition(1);//Admin
            config = new Config();
            config.setAllUsers(Arrays.asList(user));
            fms.refreshConfig();
            initialize();
        }
    }


}
