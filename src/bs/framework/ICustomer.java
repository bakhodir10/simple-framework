package bs.framework;

public interface ICustomer {
    void addAccount(IAccount account);

    void removeAccount(IAccount account);

    void sendEmailCustomer();
}
