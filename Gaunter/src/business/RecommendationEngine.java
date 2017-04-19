 
package business;

import java.util.*;

class RecommendationEngine {
    
    private int jump_state_algorithm;
    private float time_of_day_true;
    private float time_of_day_false;
    private float week_true;
    private float week_false;
    private float quarter_true;
    private float quarter_false;
    private float year_true;
    private float year_false;
    
    public RecommendationEngine(){
        this.jump_state_algorithm = 1;
        this.time_of_day_true = 1f;
        this.time_of_day_false = 0.25f;
        this.week_true = 1f;
        this.week_false = 0.25f;
        this.quarter_true = 1f;
        this.quarter_false = 0.25f;
        this.year_true = 1f;
        this.year_false = 0.25f;
    }
    
    public void setWeights(String type){
        switch(type){
            case "Regular" : this.time_of_day_true = 1f;
                             this.time_of_day_false = 0.25f;
                             this.week_true = 1f;
                             this.week_false = 0.25f;
                             this.quarter_true = 1f;
                             this.quarter_false = 0.25f;
                             this.year_true = 1f;
                             this.year_false = 0.25f;
                             break;
                             
            default : this.time_of_day_true = 1f;
                      this.time_of_day_false = 0.25f;
                      this.week_true = 1f;
                      this.week_false = 0.25f;
                      this.quarter_true = 1f;
                      this.quarter_false = 0.25f;
                      this.year_true = 1f;
                      this.year_false = 0.25f;
                      break;
        }
    }
    
    public void setJumpStateAlgorithm(String algorithm_name){
        switch(algorithm_name){
            case "PointsGame" : this.jump_state_algorithm = 1;
                                break;
            default : this.jump_state_algorithm = 1;
        }
    }
    
    public int CalcuateJumpStateRecommendation(HashMap<Integer,int[]> signatures, float [] time_vector){
        
        int res = 0;
        
        if(signatures.size()>0){
            switch(this.jump_state_algorithm){
                case 1 : res = this.pointsMethod(signatures, time_vector);
                         break;
                default : res = 0;
                          break;
            }
        }
        
        return res;
    }
    
    private int pointsMethod(HashMap<Integer,int[]> signatures, float [] time_vector){
        int size = signatures.size();
        float [] values = new float [size];
        int i,j;
        float max_value = -1;
        int max_node = 0;
        
        for(i=0; i<size; i++){
            values[i] = 0;
        }
        
        for(i=0; i<5; i++){
            for(j=0; j< size; j++ ) {
                if(time_vector[i]==1){
                    values[j] += this.time_of_day_true * signatures.get(j)[i];
                }else{
                    values[j] += this.time_of_day_false * signatures.get(j)[i];
                }
            }
        }
        
        for(i=5; i<12; i++){
            for(j=0; j< size; j++ ) {
                if(time_vector[i]==1){
                    values[j] += this.week_true * signatures.get(j)[i];
                }else{
                    values[j] += this.week_false * signatures.get(j)[i];
                }
            }
        }
        
        for(i=12; i<14; i++){
            for(j=0; j< size; j++ ) {
                if(time_vector[i]==1){
                    values[j] += this.quarter_true * signatures.get(j)[i];
                }else{
                    values[j] += this.quarter_false * signatures.get(j)[i];
                }
            }
        }
        
        for(i=14; i<16; i++){
            for(j=0; j< size; j++ ) {
                if(time_vector[i]==1){
                    values[j] += this.year_true * signatures.get(j)[i];
                }else{
                    values[j] += this.year_false * signatures.get(j)[i];
                }
            }
        }
        
        for(i=0; i<size; i++){
            if(values[i]>max_value){
                max_value = values[i];
                max_node = i;
            }
        }
        
        return max_node;
    }
    
    public Query GenerateJumpStateRecommendation(State state){
        
    }
    
    
    
}
