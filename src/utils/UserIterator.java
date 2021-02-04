package utils;

import server.models.User;

import java.util.List;
import java.util.ListIterator;

public class UserIterator {
    public void printUsers(List<User> userList){

        ListIterator<User> iterator = userList.listIterator();
        while (iterator.hasNext()){
            System.out.println("Value "+iterator.next().getFname());

        }
    }
}
