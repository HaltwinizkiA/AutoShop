package autoShop.service;

import java.util.List;

public class AService<T> {
    public String view(List<T> list){
        String line="";
        for (Object object:list){
            line=line+"\n"+object.toString();
        }

        return line;
    }
}
