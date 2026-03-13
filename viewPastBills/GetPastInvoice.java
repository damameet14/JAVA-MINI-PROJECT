package viewPastBills;

import viewPastBills.viewPastBillHelper.*;
import java.util.*;

public class GetPastInvoice {
    public static void entry(Scanner sc) {
        System.out.println("Enter the Start Date (M-D-YYYY): ");
        String startDate = sc.next();
        System.out.println("Enter the End Date (M-D-YYYY): ");
        String endDate = sc.next();
        Display.displayPastInvoice(startDate, endDate);

    }

}
