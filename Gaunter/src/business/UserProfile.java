package business;

import java.util.*;

class UserProfile {
    
    private int user_number;
    private String user_name;
    private ArrayList<State> states;
    
    public UserProfile(int user_number,String user_name, ArrayList<State> states){
        this.user_number = user_number;
        this.user_name = user_name;
        this.states = states;
    } 
}
