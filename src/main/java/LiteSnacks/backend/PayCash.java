package LiteSnacks.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PayCash {
    private static final CashHandler c = new CashHandler();
    private static List<Cash> cashReserves = c.getcashes();

    public static void setCashReserves(List<Cash> newValues) {
        cashReserves = newValues;
    }

    public static List<Cash> getCashReserves() {
        return cashReserves;
    }

    /*
    customerCash: The money the customer puts into the machine
    price: total price of goods
    change: change given by the vending machine as a string
     */
    public static String submitPayment(List<Cash> customerCash, double price){
        String change = "Something went wrong with 'addCash()";
        List<Cash> copy = clone(cashReserves);

        if(addCash(customerCash)) {
            int sum = 0;
            for(Cash c : customerCash) {
                sum += (int)(100 * c.getValue()) * c.getQty();
            }
            int changeRequired = sum - (int)(100 * price);
            change = calculateChange(changeRequired/100.0);
            if(change.equals("Insufficient Change. Please try another payment method")) {
                setCashReserves(copy);
            }
        }
        else {
            setCashReserves(copy);
        }
        CashHandler.Submit(cashReserves);
        return change;
    }

    /*
    The customer will give cash to the vending machine. This cash will be added to the vending machines total
     */
    private static boolean addCash(List<Cash> customerCash) {
        boolean success = false;
        if(verifyAllQPos(customerCash)) {
            int successes = 0;
            Cash cash;
            Cash vendingMachineCash;
            for (int i = 0; i < customerCash.size(); i++) {
                cash = customerCash.get(i);
                for (int j = 0; j < cashReserves.size(); j++) {
                    vendingMachineCash = cashReserves.get(j);
                    if (vendingMachineCash.getValue() == cash.getValue()) {
                        cashReserves.get(j).modifyqty(cash.getQty());
                        successes++;
                    }
                }
            }
            if (successes == customerCash.size()) {
                success = true;
            }
        }
        return success;
    }

    // Returns true if each cash.qty >= 0
    private static boolean verifyAllQPos(List<Cash> cash) {
        boolean verified = true;
        Cash cashObj;
        for(int i=0; i<cash.size() && verified; i++) {
            cashObj = cash.get(i);
            if(cashObj.getQty() < 0)
                verified = false;
        }
        return verified;
    }

    private static String calculateChange(double change) {
        // Convert "change" to integers to avoid floating-point errors
        int intChange = (int)(change*100);

        StringBuilder sb = new StringBuilder();
        Cash vendingMachineCash;
        int i=0;
        while(intChange > 0 && i < cashReserves.size()) {
            vendingMachineCash = cashReserves.get(i);

            // Convert Cash.getValue() from double to integer, again to avoid floating-point errors
            int cashObjectValue = (int)(100 * vendingMachineCash.getValue());
            if(intChange >= cashObjectValue && vendingMachineCash.getQty() > 0) {
                intChange = intChange - cashObjectValue;
                vendingMachineCash.modifyqty(-1);
                sb.append("$").append(vendingMachineCash.getValue()).append(" ");
            }
            else {
                i++;
            }
        }
        if(intChange != 0) {
            sb = new StringBuilder("Insufficient Change. Please try another payment method");
        }
        else if(change == 0) {
            sb.append("$0");
        }

    return sb.toString().trim();
    }

    private static List<Cash> clone(List<Cash> sweetCash) {
        List<Cash> copy = new ArrayList<>(sweetCash.size());
        Cash newCash;
        for(Cash c : sweetCash) {
            newCash = new Cash(c.getValue(), c.getQty(), c.getImg());
            copy.add(newCash);
        }
        return copy;
    }
}


