package LiteSnacks.backend;

import LiteSnacks.UI.Cash;
import java.util.List;

public class PayCash {
    List<Cash> cashReserves;

    public PayCash() {
        CashHandler c = new CashHandler();
        this.cashReserves = c.getcashes();
    }

    public boolean calculateChange(double change) {
        boolean isValid = true;
        System.out.println("change req: " + change);

        // Convert "change" to integers to avoid floating-point errors
        int intChange = (int)(change*100);

        StringBuilder sb = new StringBuilder();
        Cash cashObject;
        int cashObjectValue;
        int i=0;
        while(intChange > 0 && i < cashReserves.size()) {
            cashObject = cashReserves.get(i);

            // Convert Cash.getValue() from double to integer, again to avoid floating-point errors
            cashObjectValue = (int)(100 * cashObject.getValue());
            if(intChange >= cashObjectValue && cashObject.getQty() > 0) {
                intChange = intChange - cashObjectValue;
                cashObject.modifyqty(-1);
                sb.append(cashObject.getValue());
                sb.append(" ");
            }
            else {
                i = i + 1;
            }
        }
        if(intChange != 0) {
            System.out.println("insufficient change");
            isValid = false;
        }
        else {
            System.out.println("result: " + sb.toString());
        }
    return isValid;
    }


}


