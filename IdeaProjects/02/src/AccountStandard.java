import java.util.ArrayList;

public class AccountStandard extends Account implements AccountStandardInterface{

    public static final int MAXIMAL_LOGIN_ATTEMPTS = 3;
    private int balance;
    private ArrayList<MusicTitle> titlesBought;
    private int failedLoginAttempts;


    public AccountStandard(int balance, ArrayList<MusicTitle> titlesBought, int failedLoginAttempts) {
        this.balance = balance;
        this.titlesBought = titlesBought;
        this.failedLoginAttempts = failedLoginAttempts;
    }





    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public ArrayList<MusicTitle> getTitlesBought() {
        return titlesBought;
    }

    /**
     * Adds the amount - if positive - to the current balance. For
     * non-positive amounts the action does nothing.
     *
     * @param amount The amount being deposited.
     */
    @Override
    public void deposit(int amount) {
        this.balance += amount;
    }

    public void setTitlesBought(ArrayList<MusicTitle> titlesBought) {
        this.titlesBought = titlesBought;
    }

    @Override
    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    @Override
    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    /**
     * If the user is logged in and has sufficient funds, a music
     * title is bought by adding the title to the ArrayList of
     * titlesBought of the customer. Furthermore the price of the
     * title is subtracted from the funds of the customer. If the
     * user is not logged in, a corresponding warning is to be
     * printed. Likewise if the user has insufficient funds, they
     * should be asked to pay money into their account.
     *
     * @param musicTitle The music title that the customer wants to
     *                   buy.
     */
    @Override
    public void buy(MusicTitle musicTitle) {
        if ( this.isLoggedIn() ) {
            if ( this.balance >= musicTitle.getPrice() )
                titlesBought.add(musicTitle);
        } else {
            System.out.println("You are not login");
        }
    }

    @Override
    void loginABS(String password) {

    }

    @Override
    void logoutABS() {

    }

    @Override
    public String toString() {
        return "AccountStandard{" +
                "balance=" + balance +
                ", titlesBought=" + titlesBought +
                ", failedLoginAttempts=" + failedLoginAttempts +
                '}';
    }



}
