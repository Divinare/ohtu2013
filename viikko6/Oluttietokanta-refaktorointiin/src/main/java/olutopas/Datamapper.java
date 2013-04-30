
package olutopas;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.model.User;

public interface Datamapper {
    
    public EbeanServer getServer();
    
    public User getUser();
    
    public void setUser(User user);
    
    public Object findName(Class c, String name);
    
    public Object findID(Class c, int ID);
    
    public void save(Object o);
    
    public List getList(Class c);
}