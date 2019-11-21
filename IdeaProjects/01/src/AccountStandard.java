import java.util.ArrayList;

public class AccountStandard extends Account implements AccountStandardInterface {

    /* The max times of attempt */
    public static final int MAXIMAL_LOGIN_ATTEMPTS = 3;

    private int balance;
    private ArrayList<MusicTitle> titlesBought = new ArrayList<>();     // must be initionalized!!!
    private int failedLoginAttempts;


    public AccountStandard(String name, String salutation, String email, String password) {
        super(name, salutation, email, password);
    }


    /**
     * Getter method to return the balance.
     *
     * @return The balance of the account.
     */
    @Override
    public int getBalance() {
        return this.balance;
    }

    /**
     * We require in each sub-class the existence of a login method.
     *
     * @param password The password provided that will be compared to
     *                 the password stored on the system, i.e., the value of the
     *                 field variable. If the password provided is correct, the field
     *                 variable loggedIn is changes to true, else a warning is to be
     *                 printed.
     */
    @Override
    public void login(String password) {
        if ( failedLoginAttempts > MAXIMAL_LOGIN_ATTEMPTS  ) {
            return;
        }
        if ( this.checkPassword(password) )
        {
            this.setLoggedIn(true);
            //FIXME:
            failedLoginAttempts = 0;
        }
        else {
            failedLoginAttempts ++;
        }
    }

    /**
     * Getter method to return the list of all titles bought by the user.
     *
     * @return The current list of titles bought by the user.
     */
    @Override
    public ArrayList<MusicTitle> getTitlesBought() {
        return this.titlesBought;
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

    /**
     * Setter for the balance.
     *
     * @param balance The new balance of the account.
     */
    @Override
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Getter method to return the number of failed login attempts.
     *
     * @return The number of failed login attempts.
     */
    @Override
    public int getFailedLoginAttempts() {
        return this.failedLoginAttempts;
    }

    /**
     * Setter for the failed login attempts.
     *
     * @param failedLoginAttempts The new number of failed login attempts.
     */
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
        if ( getLoggedIn() && this.balance >= musicTitle.getPrice() ) {
            titlesBought.add(musicTitle);
            balance -= musicTitle.getPrice();
        }
    }

    /**
     * The user is no longer logged in, that is, the
     * loggedIn variable set to false.
     */
    @Override
    public void logout() {
        this.setLoggedIn(false);
    }

    /**
     * Method to compare a provided password with the stored password.
     *
     * @param password The provided password to which the password of
     *                 the this object is compared.
     * @return true if the password of the account agrees with the
     * argument, false else.
     */
    @Override
    public boolean checkPassword(String password) {

        /* check the password */
        return password.equals(this.getPassword());
    }

    /**
     * Changes the password from old to new if the old password is
     * correct, else an error message is printed.
     *
     * @param oldPassword The current password.
     * @param newPassword The future password.
     */
    @Override
    public void changePassword(String oldPassword, String newPassword) {
        if ( this.checkPassword(oldPassword) )
            this.setPassword(newPassword);
    }


}
