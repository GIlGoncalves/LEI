package business;

import java.util.*;

class MarkovChain_States {
    
    private HashMap<Integer,Node> nodes;
    private HashSet<Ark> arks;
    private int number_of_nodes;
    private int number_of_arks;
    
    public MarkovChain_States(){
        this.nodes = new HashMap<Integer,Node>();
        this.nodes.put(-2, new Final_Node());
        this.nodes.put(-1, new Start_Node());
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
    
    public Recommendation Insert_Query(Query previous_query, Query next_query){
        int start_state = 0;
        int end_state = 0;
        
        if(!previous_query.isValid()){
            start_state = -1;
        } else{
            start_state = this.getState_Number(previous_query.getState());
        }
        
        end_state =  this.getState_Number(next_query.getState());
        
        if(end_state == 0){
            end_state = this.addNode(next_query.getState());
        }
        
        if(start_state != end_state){
            this.Register_State_Jump(start_state, end_state);
        }
        
        return this.Generate_Recommendation(next_query, end_state);
        
    }
    
    private Recommendation Generate_Recommendation(Query user_query, int node){
        Query same_state_recommendation = this.nodes.get(node).Generate_Recommendation();
        
        Query jump_state_recommendation = this.Generate_Jump_State_Recommendation(node);
                
        return new Recommendation(user_query, same_state_recommendation, jump_state_recommendation);
    }
    
    private Query Generate_Jump_State_Recommendation(int node){
        
        Query recommendation = null;
        HashSet<Ark> candidate_arks = this.getArks_Start_Node(node);
        HashMap<Integer,int[]> int_vector_map = new HashMap<>();
        HashMap<Integer,Ark> int_ark_map = new HashMap<>();
        int i=1, max_ark = 0;
        int max_node = 0;
        float [] time_vector;
            
        for(Ark ark : candidate_arks){
            int_vector_map.put(i, ark.ark_signature);
            int_ark_map.put(i, ark);
            i++;
        }
        
        RecommendationEngine re = new RecommendationEngine();
        re.setJumpStateAlgorithm("PointsGame");
        
        TimeManager tm = new TimeManager();
        time_vector = tm.getTimeFieldsF();
        
        max_ark = re.CalcuateJumpStateRecommendation(int_vector_map, time_vector);
        
        if(max_ark == 0 || max_ark == -2){
            recommendation = this.No_Next_State_Recommendation(node);
        }else {
            max_node = int_ark_map.get(max_ark).end_node;
            recommendation = re.GenerateJumpStateRecommendation(this.nodes.get(max_node).getState());
        }
        
        return recommendation;
    }
    
    private Query No_Next_State_Recommendation(int node){
        
    }
    
    private int getState_Number(State state){
        int res = 0;
        
        for(Node n : this.nodes.values()){
            if(n.state.equals(state)){
                res = n.reference;
                break;
            }
        }
        
        return res;
    }
    
    private int addNode(State new_state){
        
        this.number_of_nodes++;
        Node new_node = new Node(this.number_of_nodes, new_state.clone());
        this.nodes.put(new_node.reference, new_node);
        return new_node.reference;
    }
    
    private void Register_State_Jump(int start_state, int end_state){
        Ark search_ark = new Ark(start_state, end_state);
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
            this.number_of_arks++;
        } 
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
        HashSet<Ark> res = new HashSet<>();
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
        
        public Node(){
            this.reference = 0;
            this.state = null;
        }
        
        public State getState(){
            return this.state;
        }
        
        public Query Generate_Recommendation(){
            return this.state.Generate_Recommendation();
        }
        
        public Query Generate_Recommendation(Query user_query){
            return this.state.Generate_Recommendation(user_query);
        }
    }
    
    private class Final_Node extends Node{
        
        public Final_Node(){
            this.reference = -2;
            this.state = null;
        }
                 
    }
    
    private class Start_Node extends Node{
        
        public Start_Node(){
            this.reference = -1;
            this.state = null;
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
        
        public void setWeight(float new_weight){
            this.weight = new_weight;
        }

        public void setProbability(float probability) {
            this.probability = probability;
        }
        
        public void calculate_Weight(){
            TimeManager tm = new TimeManager();
            float [] time_function = tm.getTimeFunction();
            
            
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


