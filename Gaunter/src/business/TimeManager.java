package business;

import java.util.*;

class TimeManager {
    
    //Time of day Related
    private Hour early_morning_end;
    private Hour late_morning_end;
    private Hour early_afternoon_end;
    private Hour late_afternoon_end;
    private Hour work_day_start;
    //Quarter Related
    private Date first_quarter_end;
    private Date second_quarter_end;
    private Date third_quarter_end;
    //Fiscal Year Related
    private Date fiscal_year_end;
    private Date fiscal_year_start;
    
    private static float time_of_day_true = 0.5f;
    private static float time_of_day_false = 1/8;
    private static float week_day_true = 0.25f;
    private static float week_day_false = 3/24;
    private static float quarter_true = 0.75f;
    private static float quarter_false = 0.25f;
    private static float fiscal_true = 0.75f;
    private static float fiscal_false = 0.25f;

    public TimeManager() {
        this.early_morning_end = new Hour(8,59);
        this.late_morning_end = new Hour(11,59);
        this.early_afternoon_end = new Hour(14,59);
        this.late_afternoon_end = new Hour(18,59);
        this.work_day_start = new Hour(4,59);
        this.first_quarter_end = new Date(3,31);
        this.second_quarter_end = new Date(6,30);
        this.third_quarter_end = new Date(9,30);
        this.fiscal_year_end = new Date(12,31);
        this.fiscal_year_start = new Date(1,1);
    }
    
    public int[] getTimeFields(){
         int [] res = new int[16];
        
        for(int i = 0; i<16; i++){
            res[i]=0;
        }
        
        res[this.getTime_of_Day()]=1;
        res[this.getWeek_Day()]=1;
        res[this.getQuarter()]=1;
        res[this.getFiscal_Year()]=1;
            
        return res;
    }
    
    public float[] getTimeFieldsF(){
        float [] res = new float[16];
        
        for(int i = 0; i<16; i++){
            res[i]=0;
        }
        
        res[this.getTime_of_Day()]=1;
        res[this.getWeek_Day()]=1;
        res[this.getQuarter()]=1;
        res[this.getFiscal_Year()]=1;
            
        return res;
    }
    
    public float[] getTimeFunction(){
        float [] time_vector = this.getTimeFieldsF();
        int i = 0;
        
        for(i=0; i< 5; i++){
            if(time_vector[i]==1){
                time_vector[i] = time_of_day_true;
            } else {
                time_vector[i] = time_of_day_false;
            }
        }
        
        for(i=5; i<12; i++){
            if(time_vector[i]==1){
                time_vector[i] = week_day_true;
            } else {
                time_vector[i] = week_day_false;
            }
        }
        
        for(i=12;i<14;i++){
            if(time_vector[i]==1){
                time_vector[i] = quarter_true;
            } else {
                time_vector[i] = quarter_false;
            }
        }
        
        for(i=14;i<16;i++){
            if(time_vector[i]==1){
                time_vector[i] = fiscal_true;
            } else {
                time_vector[i] = fiscal_false;
            }
        }
        
        return time_vector;
    }
    
    private int getTime_of_Day(){
        int res = 0;
        GregorianCalendar now = new GregorianCalendar();
        
        if(this.late_afternoon_end.isBefore(now) || !this.work_day_start.isBefore(now))
        {
            res = 4;
        } else if(!this.early_morning_end.isBefore(now))
        {
            res = 0;
        } else if(!this.late_morning_end.isBefore(now))
        {
            res = 1;
        } else if(!this.early_afternoon_end.isBefore(now))
        {
            res = 2;
        } else 
        {
            res = 3;
        }
        
        return res;
     
    }
    
    private int getWeek_Day(){
        GregorianCalendar now = new GregorianCalendar();

        return ((now.get(GregorianCalendar.DAY_OF_WEEK)+5) % 7 ) + 5;
        }
    
    private int getQuarter(){
        GregorianCalendar now = new GregorianCalendar();
        int res = 13;
        
        if(this.first_quarter_end.One_Week_Before(now) || this.second_quarter_end.One_Week_Before(now) || this.third_quarter_end.One_Week_Before(now) || this.fiscal_year_end.One_Week_Before(now)){
            res = 12;
        }
        
        return res;
    }
    
    private int getFiscal_Year(){
        GregorianCalendar now = new GregorianCalendar();
        int res = 15;
        
        if(this.fiscal_year_end.One_Week_Before(now)){
            res = 14;
        }
        
        return res;
    }
    
    class Hour{
        public int hour;
        public int minute;
        
        public Hour(int hour, int minute){
            this.hour = hour;
            this.minute = minute;
        }
        
        public Boolean isBefore(GregorianCalendar time_now){
            Boolean res = false;
            
            int hour_now = time_now.get(GregorianCalendar.HOUR_OF_DAY);
            int minute_now = time_now.get(GregorianCalendar.MINUTE);
            
            if(hour_now > this.hour || (hour_now==this.hour && minute_now>this.minute)){
                res = true;
            }
            
            return res;
        }
        
    }
    
    class Date{
        
        public int month;
        public int day;

        public Date(int month, int day) {
            this.month = month;
            this.day = day;
        }
        
        public Boolean isBefore(GregorianCalendar date_now){
            Boolean res = false;
            
            int month_now = date_now.get(GregorianCalendar.MONTH)+1;
            int day_now = date_now.get(GregorianCalendar.DAY_OF_MONTH);
            
            if(month_now > this.month || (month_now == this.month && day_now > this.day)){
                res = true;
            }
            
            return res;
        }
        
        public Boolean One_Week_Before(GregorianCalendar date_now){
            Boolean res = false;
            
            long theFuture = System.currentTimeMillis() + (86400 * 7 * 1000);
            GregorianCalendar week_from_now = new GregorianCalendar();
            week_from_now.setTimeInMillis(theFuture);
            
            if(!this.isBefore(date_now) && this.isBefore(week_from_now)){
                res = true;
            }
            
            return res;
        }
        
    }
}
