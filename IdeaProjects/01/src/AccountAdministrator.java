import java.util.ArrayList;

/**
 * The root user class
 */
public class AccountAdministrator extends Account implements AccountAdministratorInterface {

    /* The user accounts container */
    private ArrayList<Account> accounts = new ArrayList<>();    // MUST be inited!!

    /**
     * Constructor
     * @param name
     * @param salutation
     * @param email
     * @param password
     */
    public AccountAdministrator(String name, String salutation, String email, String password) {
        super(name, salutation, email, password);
    }


    /**
     * Setter for accounts
     * @param accounts
     */
    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    @Override
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * Reset the account
     * @param account The account that is to be reset.
     * @param password The new password for the account that is to be
     */
    @Override
    public void resetAccount(Account account, String password) {

        /* get the olg password of the account */
        String oldPassword = account.getPassword();

        /* change the password first */
        account.changePassword(oldPassword, password);

        /* check if the instance of AccountStandard */
        if ( (account instanceof AccountStandard) ) {
            /* the reset the failedLoginAttempts */
            ((AccountStandard) account).setFailedLoginAttempts(0);

        }
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

        /* check the password */
        if ( this.checkPassword(password) ) {
            /* The set the login status to true */
            this.setLoggedIn(true);
        } else {
            /* set to false */
            this.setLoggedIn(false);
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
        return this.getPassword().equals(password);
    }

    @Override
    public String toString() {
        return "AccountAdministrator{" +
                "accounts=" + accounts +
                '}';
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

        /* check the password */
        if ( this.checkPassword(oldPassword) ) {

            /* Set the new password */
            this.setPassword(newPassword);
        }
    }
}
