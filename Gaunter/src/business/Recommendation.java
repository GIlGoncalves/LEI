package business;

class Recommendation {
    
    private Query user_query;
    private Query same_state_recommendation;
    private Query jump_state_recommendation;
    
    public Recommendation(Query user_query, Query same_state_recommendation, Query jump_state_recommendation){
        this.user_query = user_query;
        this.same_state_recommendation = same_state_recommendation;
        this.jump_state_recommendation = jump_state_recommendation;
    }
    
}
