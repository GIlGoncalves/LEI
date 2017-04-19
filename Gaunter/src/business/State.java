package business;

import java.util.*;
        
class State {
    
    public int state_id;
    public String fact_table;
    public ArrayList<String> dimensions;
    public ArrayList<Preference> preferences;
    
    public State(){
        
    }
    
    public Query Generate_Recommendation(){
        return new Query();
    }
    
    public Query Generate_Recommendation(Query user_query){
        return new Query();
    }
    
    public Boolean equals(){
        return false;
    }
    
    public State clone(){
        return this;
    }
    
}
