package model.comporators.master;

import model.entity.master.Master;

import java.util.Comparator;


public class MasterComparator  {
private Comparator<Master> masterBusySort=new Comparator<Master>() {
    @Override
    public int compare(Master o1, Master o2) {
        return  o1.getStatus()-o2.getStatus();
    }
};
    public Comparator<Master> masterNameSort=new Comparator<Master>() {
        @Override
        public int compare(Master o1, Master o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public Comparator<Master> getMasterBusySort() {
        return masterBusySort;
    }
    public Comparator<Master> getMasterNameSort(){
        return masterNameSort;
}
}
