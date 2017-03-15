
package business;

import java.io.*;

public class testeClass {
    
    public static void main(String[] args){
        TimeManager tm = new TimeManager();
        int [] res = tm.getTimeFields();
        
        for(int i = 0; i<16; i++){
            System.out.println(i + " :: " + res[i]);
        }
    }
    
}
