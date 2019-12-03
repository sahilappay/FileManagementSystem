package service.impl;

import config.Initializer;
import bean.User;
import dao.inter.UserDaoInter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class UserServiceImpl extends AbstractUserService {

    protected UserServiceImpl(){

    }

    private UserDaoInter userDaoInter = DI.userDao();
    @Override
    public User askAllFieldsToClient() {
        //mushteriden user haqqinda her sheyi sorushub user obyekti qaytarirsiniz
        Scanner scan = new Scanner(System.in);
        System.out.println("Please insert your name: ");
        String name = scan.nextLine();
        System.out.println("Please insert your surname: ");
        String surname=scan.nextLine();
        System.out.println("Please insert your username: ");
        String username=scan.nextLine();
        System.out.println("Please insert your password: ");
        String password =scan.nextLine();
        User user = new User(name,surname,username,password);
        System.out.println("Please wait admin's accept! Thank you!");
        return user;
    }


    @Override
    public User askUsernameAndPassword() {
        //userin username ve parolunu sorushub user obyekti qaytarirsiniz
        Scanner scan = new Scanner(System.in);
        System.out.println("Please insert your username: ");
        String username=scan.nextLine();
        System.out.println("Please insert your password: ");
        String password =scan.nextLine();
        User user = new User(username,password);
        return user;
    }

    @Override
    public User getLoggedInUser(){
        return Initializer.config.getLoggedInUser();
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoInter.getAllUsers();
    }

    @Override
    public List<User> getAllInActiveUsers() {
        return userDaoInter.getAllInActiveUsers();
    }

    @Override
    public List<User> getAllActiveUsers() {
        return userDaoInter.getAllActiveUsers();
    }

    @Override
    public boolean register() {
        User user = askAllFieldsToClient();
        return userDaoInter.add(user);
    }

    @Override
    public boolean approveUser() {
        printAllInActiveUsers();
        String id = askForUserId();
        User user = getUserById(id);
        user.setStatus(1);
        return userDaoInter.save(user);
    }

}
