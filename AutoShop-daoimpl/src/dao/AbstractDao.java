package dao;

import java.util.Collections;
import java.util.List;

public abstract class AbstractDao<T> {
    public String view(List<T> list){
        String line="";
        for (Object object:list){
            line=line+object.toString();
        }

        return line;
    }
    abstract public List<T>  getall();
}
