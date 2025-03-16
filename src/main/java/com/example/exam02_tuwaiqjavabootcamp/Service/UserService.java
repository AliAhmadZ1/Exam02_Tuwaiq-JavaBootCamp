package com.example.exam02_tuwaiqjavabootcamp.Service;


import com.example.exam02_tuwaiqjavabootcamp.Model.Book;
import com.example.exam02_tuwaiqjavabootcamp.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {



    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getAllUsers(){
        return users;
    }

    public boolean addUser(User user){
        for (User u: users){
            if (u.getId().equals(user.getId()))
                return false;
        }
        users.add(user);
        return true;
    }

    public boolean updateUser(String id,User user){
        for (User u: users) {
            if (u.getId().equals(id)) {
                users.set(users.indexOf(u), user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id){
        for (User u: users) {
            if (u.getId().equals(id)) {
                users.remove(u);
                return true;
            }
        }
        return false;
    }

    //9. Create an endpoint that takes a balance and then returns all users who
    //have this balance or above.
    public ArrayList<User> searchBalance(int balance){
        ArrayList<User> balanceList = new ArrayList<>();

        for (User u:users){
            if (u.getBalance()>=balance){
                balanceList.add(u);
            }
        }
        return balanceList;
    }
    //10.Create an endpoint that takes an age then return all Users who have this
    //age or above.
    public ArrayList<User> searchAge(int age){
        ArrayList<User> ageList = new ArrayList<>();

        for (User u:users){
            if (u.getAge()>=age){
                ageList.add(u);
            }
        }
        return ageList;
    }

    public boolean librarianRole(String id){
        for (User u:users){
            if (u.getRole().equals("librarian"))
                return true;
        }
        return false;
    }
}
