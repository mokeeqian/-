import java.util.ArrayList;

public class AccountAdministrator extends Account implements AccountAdministratorInterface {

    /* The user accounts */
    private ArrayList<Account> accounts;


    public AccountAdministrator(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }




    @Override
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    /**
     * The method adds an account to the list of accounts the
     * administrator looks after.
     *
     * @param account
     */
    @Override
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void resetAccount(Account account, String password) {

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

    }



    /**
     * The user is no longer logged in, that is, the
     * loggedIn variable set to false.
     */
    @Override
    public void logout() {

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
        return password == this.getPassword();
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

    }
}
