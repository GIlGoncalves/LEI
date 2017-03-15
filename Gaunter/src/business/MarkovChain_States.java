package business;

import java.util.*;

class MarkovChain_States {
    
    private HashMap<Integer,Node> nodes;
    private HashSet<Ark> arks;
    private int number_of_nodes;
    private int number_of_arks;
    
    public MarkovChain_States(){
        this.nodes = new HashMap<Integer,Node>();
        this.arks = new HashSet<Ark>();
        this.number_of_arks = 0;
        this.number_of_nodes = 0;
    }
    
    public MarkovChain_States(HashMap<Integer,Node> nodes, HashSet<Ark> arks, int number_of_nodes, int number_of_arks){
        this.nodes = nodes;
        this.arks = arks;
        this.number_of_arks = number_of_arks;
        this.number_of_nodes = number_of_nodes;
    }
    
    public void addNode(State new_state){
        this.number_of_nodes++;
        Node new_node = new Node(this.number_of_nodes, new_state);
        this.nodes.put(new_node.reference, new_node);
    }
    
    public void addArk(int start_node, int end_node){
        Ark new_ark = new Ark(start_node, end_node);
        this.number_of_arks++;
        this.arks.add(new_ark);
    }
    
    
    public void Register_State_Jump(State start_state, State end_state){
        
        Ark search_ark = new Ark(start_state.state_id, end_state.state_id);
        Boolean found_ark = false;
        for(Ark ark : this.arks){
            if(search_ark.equals(ark)){
                ark.Register_Jump();
                found_ark = true;
                break;
            }
        }    
        if(!found_ark){
            search_ark.Register_Jump();
            this.arks.add(search_ark);
        }   
    }
    
    private void Calculate_Weights(){
        
    }
    
    private void Calculate_Weights_fromNode(int node){
        HashSet<Ark> arks_to_calculate = this.getArks_Start_Node(node);
        float sum_of_weights = 0;
        
        for(Ark ark : arks_to_calculate){
            ark.calculate_Weight();
            sum_of_weights += ark.weight;
        }
        
        for(Ark ark : arks_to_calculate){
            ark.probability = ark.weight/sum_of_weights; 
        }
    }
    
    private HashSet<Ark> getArks_Start_Node(int node){
        HashSet<Ark> res = new HashSet<Ark>();
        for(Ark ark : this.arks){
            if(ark.start_node == node){
                res.add(ark);
            }
        }
        return res;
    }
 
    private class Node{
        public int reference;
        public State state;
        
        public Node(int reference, State state){
            this.reference = reference;
            this.state = state;
        }
    }
    
    private class Ark{
        public int start_node;
        public int end_node;
        public float weight;
        public float probability;
        public int [] ark_signature;
        /*
        //Signature values
        
        //Time of day related
        public int early_morning; :: 0 
        public int late_morning; :: 1
        public int early_afternoon; :: 2
        public int late_afternoon; :: 3
        public int off_business_hours; :: 4
        
        //Week related
        public int monday; :: 5
        public int thuesday; :: 6
        public int wednesday; :: 7
        public int thursday; :: 8
        public int friday; :: 9
        public int saturday; :: 10
        public int sunday; :: 11 
         
        //Quarter Related
        public int last_week_of_quarter; :: 12
        public int rest_of_quarter; :: 13
        
        //Fiscal Year Related 
        public int last_week_of_year; :: 14
        public int rest_of_year; :: 15 */
        
        

        public Ark(int start_node, int end_node, float weight, float probability , int[] ark_signature) {
            this.start_node = start_node;
            this.end_node = end_node;
            this.weight = weight;
            this.ark_signature = ark_signature;
            this.probability = probability;
        }
        

        public Ark(int start_node, int end_node){
            this.start_node = start_node;
            this.end_node = end_node;
            this.ark_signature = new int[16];
            for(int i = 0; i<16; i++){
                this.ark_signature[i]=0;
            }
        }
        
        public void Register_Jump(){
            TimeManager tm = new TimeManager();
            int [] signature_increment = tm.getTimeFields();
            for(int i=0 ; i<16; i++){
                if(signature_increment[i] == 1) {
                    this.ark_signature[i]++;
                }
            }
        }
        
        public void setWeight(int new_weight){
            this.weight = new_weight;
        }

        public void setProbability(float probability) {
            this.probability = probability;
        }
        
        public void calculate_Weight(){
            
        }
        
        public Boolean equals(int start_node, int end_node){
            Boolean res = false;
            if(this.start_node == start_node && this.end_node == end_node){
                res = true;
            }
            return res;
        }
    }
}


