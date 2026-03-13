package newBill;

import newBill.newBillHelper.*;
import choiceP.*;
import java.util.*;

class CreateNewBill {
    public static void entry(Scanner sc) {

        CustomerData.entry(sc);
        ProductsData.entry(sc);

        Choice.entry();
    }
}
