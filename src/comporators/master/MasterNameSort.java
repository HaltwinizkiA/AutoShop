package comporators.master;

import model.master.Master;

import java.util.Comparator;

public class MasterNameSort implements Comparator<Master> {
    @Override
    public int compare(Master o1, Master o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
