package business;

import java.util.*;

class UserProfile {
    
    private int user_number;
    private String user_name;
    private MarkovChain_States markov_chain;

    public UserProfile(int user_number, String user_name, MarkovChain_States markov_chain) {
        this.user_number = user_number;
        this.user_name = user_name;
        this.markov_chain = markov_chain;
    }
    
    
    public Recommendation Insert_Query(Query user_query, Boolean state_jump){
        
        
    }
    
    public void Feedback_Recommendation(Query user_query, Boolean feedback_value){
        
    }
    
    public void Feedback_Jump(Query user_query, Boolean feedback_value){
        
    }
    
}
