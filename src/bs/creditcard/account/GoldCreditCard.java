package bs.creditcard.account;

import bs.framework.customer.ICustomer;

public class GoldCreditCard extends CreditCardAccount {

    public static double mi = 0.06;
    public static double mp = 0.1;

    public GoldCreditCard(String accNum, ICustomer customer, String ccNumber, String expireDate) {
        super(ccNumber, customer, ccNumber, expireDate);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double getNewMonthlyBalance() {

        double lastMonthBalance = getLastMonthBalance();
        double totalMonthlyCredits = getTotalMonthlyCredits();
        double totalMonthlyCharges = getTotalMonthlyCharges();

        return lastMonthBalance + totalMonthlyCredits + totalMonthlyCharges + mi * (lastMonthBalance - totalMonthlyCredits);
    }

    @Override
    public double getNewMonthlyAmountDue() {
        double lastMonthBalance = getLastMonthBalance();
        return mp * lastMonthBalance;
    }

    @Override
    public void addInterest() {
        super.setBalance(super.getBalance() + super.getBalance() * mi);
    }

    @Override
    public String getType() {
        return "Gold";
    }

}
