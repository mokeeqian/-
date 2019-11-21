import java.util.ArrayList;

/**
 *  A standard account is an account for customers who can download
 *  music from the music web site. They are represented by 3 field
 *  variables: balance, titlesBought, and failedLoginAttempts of types
 *  int, ArrayList<MusicTitle>, and int, respectively. Only three
 *  login attempts are possible. If the password is entered 3 times
 *  incorrectly, the account is disabled and only an administrator can
 *  reinstate it.
 * 
 *  @author Manfred Kerber
 *  @version 2019-11-04
 */
public interface AccountStandardInterface {
    /**
     * Getter method to return the balance.
     * @return The balance of the account.
     */
    public int getBalance();
    
    /**
     *  Method for a user to log in to their account by providing a
     *  password. It is first checked whether the account is still
     *  active (that is, not too many failed login attempts were made
     *  in the past) and secondly whether the password provided is
     *  correct. In case of a correct login the number of login
     *  attempts is reset to 0, else increased by 1.
     *  @param password The password provided for the login; this is
     *  to be compared to the password stored on the system.
     */
    public void login(String password);

    /**
     * Getter method to return the list of all titles bought by the user. 
     * @return The current list of titles bought by the user.
     */
    public ArrayList<MusicTitle> getTitlesBought();

    /** 
     * Adds the amount - if positive - to the current balance. For
     * non-positive amounts the action does nothing.
     * @param amount The amount being deposited.
     */
    public void deposit(int amount);

    /**
     *  Setter for the balance.
     *  @param balance The new balance of the account.
     */
    public void setBalance(int balance);

    /**
     * Getter method to return the number of failed login attempts.
     * @return The number of failed login attempts.
     */
    public int getFailedLoginAttempts();

    /**
     *  Setter for the failed login attempts.
     *  @param failedLoginAttempts The new number of failed login attempts.
     */
    public void setFailedLoginAttempts(int failedLoginAttempts);

    /** 
     *  If the user is logged in and has sufficient funds, a music
     *  title is bought by adding the title to the ArrayList of
     *  titlesBought of the customer. Furthermore the price of the
     *  title is subtracted from the funds of the customer. If the
     *  user is not logged in, a corresponding warning is to be
     *  printed. Likewise if the user has insufficient funds, they
     *  should be asked to pay money into their account.
     *  @param musicTitle The music title that the customer wants to
     *  buy.
     */
    public void buy(MusicTitle musicTitle);
            
    /**
     * Standard toString method to represent the object in a human
     * readable form. If the user is not logged in, only a warning
     * should be printed, but no substantial information be given.
     * @return The object in a human readable form.
     */
    public String toString();
}
