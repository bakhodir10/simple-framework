package bs.framework;

public class ProxyTransaction implements TransactionStrategy {
    private TransactionStrategy transactionStrategy;
    private ILogging logging;
    private IMessage message;

    public ProxyTransaction(TransactionStrategy transactionStrategy, ILogging logging,
                            IMessage message) {
        this.transactionStrategy = transactionStrategy;
        this.logging = logging;
        this.message = message;
    }

    @Override
    public void transact(IAccount account, double amount) {
        this.transactionStrategy.transact(account, amount);
        if (amount > 500 || account.getBalance() < 0) this.message.sendMessage(account);
        this.logging.log(account, amount);
    }
}