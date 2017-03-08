package business;

import java.util.*;

public class Login_Token {
    
    private boolean valid_token;
    private int user_number;
    private String user_name;
    private GregorianCalendar login_time;
    private GregorianCalendar last_activity;
    private ArrayList<Recommendation> queries;
    
    public Login_Token(){
        this.valid_token = false;
        this.user_number = -1;
        this.user_name = "";
        this.login_time = new GregorianCalendar();
        this.last_activity = new GregorianCalendar();
    }
    
    public Login_Token(int user_number, String user_name){
        this.valid_token = true;
        this.user_number = user_number;
        this.user_name = user_name;
        this.login_time = new GregorianCalendar();
        this.last_activity = new GregorianCalendar();
    }
    
    public void Invalidate_Token(){
        this.valid_token = false;
        this.last_activity = new GregorianCalendar();
    }
    
    public void Register_Activity(){
        if(this.isValid()){
            this.last_activity = new GregorianCalendar();
        }
    }
    
    public Boolean isValid(){
        return this.valid_token;
    }
    
}
