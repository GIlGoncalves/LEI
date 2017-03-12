package business;

import java.util.*;

public class Query {
    
    private boolean valid_query;
    private String query_body_pure;
    private State query_state;
    private ArrayList<Preference> explicit_preferences;
    
    public Query(){
        this.valid_query = false;
        this.query_body_pure = "";
        this.query_state = null;
        this.explicit_preferences = new ArrayList<Preference>();
    }
    
    public Query(String query_body){
        this.query_body_pure = query_body;
        if(this.Validate_Query()){
            this.Process_Query();
        }else{
            this.query_state = null;
            this.explicit_preferences = new ArrayList<Preference>();
        }
    }
    
    private void Process_Query(){
        
    }
    
    private Boolean Validate_Query(){
        return true;
    }
}
