package business;

import java.util.*;
        
class State {
    
    public int state_id;
    public String fact_table;
    public ArrayList<String> dimensions;
    public ArrayList<Preference> preferences;
    public ArrayList<JumpState> jump_states;
    
    public State(){
        
    }
    
}
