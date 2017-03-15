package business;

import java.util.*;

class SessionHolder {
    private static HashMap<Integer,Session> sessions;
    public static Session getSession(int user_number){
        return sessions.get(user_number);
    }
    public static void addSession(Session user_session){
        sessions.put(user_session.getUser_Number(), user_session);
    }
    public static void Remove_Session(int user_number){
        sessions.remove(user_number);
    }
}
