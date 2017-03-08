package business;

import java.util.*;

class Session {
    
    private boolean Active_session;
    private int user_number;
    private String user_name;
    private UserProfile user_profile;
    private GregorianCalendar login_time;
    private GregorianCalendar last_activity;
    private ArrayList<Recommendation> queries;
    
    
    public Session(int user_number, String user_name, UserProfile user_profile){
        this.Active_session = true;
        this.user_number = user_number;
        this.user_name = user_name;
        this.user_profile = user_profile;
        this.login_time = new GregorianCalendar();
        this.last_activity = new GregorianCalendar();
    }
    
    public void Terminate_Session(){
        this.Active_session = false;
        this.last_activity = new GregorianCalendar();
    }
    
    public void Register_Query(String user_query){
        this.Register_Activity();
    }
    
    public void Register_Feedback_Recommendation(String user_query, boolean feedback_value){
        this.Register_Activity();
    }
    
    public void Register_Feedback_Jump(String user_query, boolean feedback_value){
        this.Register_Activity();
    }
        
    public Boolean isValid(){
        return this.Active_session;
    }
    
    private void Register_Activity(){
        if(this.isValid()){
            this.last_activity = new GregorianCalendar();
        }
    }
    
    
    
}
