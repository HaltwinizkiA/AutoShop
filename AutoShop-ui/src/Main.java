import facade.AutoShopAdministrator;
import model.entity.car.Car;
import model.entity.work.Work;
import model.enums.OrderStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AutoShopAdministrator.getInstance().copyOrderAndModify(123,null,null,null,null,null,null,null);

    }
}
