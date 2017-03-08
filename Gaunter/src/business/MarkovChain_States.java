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
    
    
    public void Change_Ark_Values(int start_node, int end_node, float new_weight){
        
    }
    
    public void Register_State_Jump(State start_state, State end_state){
        
    }
    
    public void Calculate_Weights(){
        
    }
    
    public void Calculate_Weights_fromNode(int node){
        
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
        
        public Ark(int start_node, int end_node, float weight){
            this.start_node = start_node;
            this.end_node = end_node;
            this.weight = weight;
        }
        
        public void setWeight(int new_weight){
            this.weight = new_weight;
        }
    }
}


