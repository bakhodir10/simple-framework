package bs.creditcard;

import bs.framework.*;

import java.util.Date;
import java.util.List;

public class CreditCardAccount extends Account {
    private String ccNumber;

    private String expireDate;

    public CreditCardAccount(String accNum, ICustomer customer, String ccNumber, String expireDate) {
        super(accNum, customer);
        this.ccNumber = ccNumber;
        this.expireDate = expireDate;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public double getLastMonthBalance() {
        int lastMonth = new Date().getMonth();
        double lastMonthBalance = 0;
        List<ILogging> histories = History.getInstance().getHistories();

        for (ILogging logging : histories) {
            if (logging.getAccount().getAccNum().equals(this.getAccNum()) && logging.getDate().getTime() < lastMonth) {
                if (logging.getType() == TransferType.DEPOSIT) lastMonthBalance += logging.getAmount();
                else lastMonthBalance -= logging.getAmount();
            }
        }
        return lastMonthBalance;
    }

    public double getTotalMonthlyCredits() {
        int lastMonth = new Date().getMonth();
        double totalMonthlyCredits = 0;
        List<ILogging> histories = History.getInstance().getHistories();

        for (ILogging logging : histories) {
            if (logging.getAccount().getAccNum().equals(this.getAccNum()) && logging.getDate().getTime() > lastMonth) {
                if (logging.getType() == TransferType.DEPOSIT) totalMonthlyCredits += logging.getAmount();
            }
        }
        return totalMonthlyCredits;
    }

    public double getTotalMonthlyCharges() {
        int lastMonth = new Date().getMonth();
        double totalMonthlyCharges = 0;
        List<ILogging> histories = History.getInstance().getHistories();

        for (ILogging logging : histories) {
            if (logging.getAccount().getAccNum().equals(this.getAccNum()) && logging.getDate().getTime() > lastMonth) {
                if (logging.getType() == TransferType.WITHDRAW) totalMonthlyCharges += logging.getAmount();
            }
        }
        return totalMonthlyCharges;
    }

//    // new balance = previous balance – total credits + total charges + MI * (previous balance – total credits)
    public double getNewMonthlyBalance() {
        return 0;
    }

    public double getNewMonthlyAmountDue() {
        return 0;
    }
}
