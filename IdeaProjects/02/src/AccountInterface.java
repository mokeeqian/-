import java.util.ArrayList;

/**
 *  The Account class should have 5 field variables, namely, name,
 *  salutation, email, password, and loggedIn of types String, String,
 *  String, String, and boolean.
 *
 *  @author Manfred Kerber
 *  @version 2019-11-04
 */
public interface AccountInterface {

    /**
     *  We require in each sub-class the existence of a login method.
     *  @param password The password provided that will be compared to
     *  the password stored on the system, i.e., the value of the
     *  field variable. If the password provided is correct, the field
     *  variable loggedIn is changes to true, else a warning is to be
     *  printed.
     */
    public void login(String password);

    /**
     *  Getter to check whether a user is logged in.
     *
     *  If the user is not loggedIn the toString method should be
     *  disabled and only a warning should be printed that first a
     *  login is necessary. Further methods may be disabled in
     *  sub-classes.
     *
     *  @return true if the user is looged in, false else.
     */
    public boolean getLoggedIn();

    /**
     *  setter for loggedIn
     *  @param loggedIn New value for the variable loggedIn
     */
    public void setLoggedIn(boolean loggedIn);

    /**
     *  The user is no longer logged in, that is, the
     *  loggedIn variable set to false.
     */
    public void logout();

    /**
     * Getter method to return the name of the user.
     * @return The name of the user of the account.
     */
    public String getName();
    
    /**
     * Getter method to return the salutation of the account holder.
     * @return The salutation of the account holder (e.g., "Mr", "Ms",
     * "Mrs", "Dr", "Prof")
     */
    public String getSalutation();

    /**
     * Getter method to return the email address of the account holder.
     * @return The email address of the account holder.  
     */
    public String getEmail();

    /**
     * Getter method to return the password. 
     * @return The password of the account.
     */
    public String getPassword();

    /**
     *  Setter for the password.
     *  @param password The new password.
     */
    public void setPassword(String password);

    /**
     * Method to compare a provided password with the stored password.
     * @param password The provided password to which the password of
     * the this object is compared.
     * @return true if the password of the account agrees with the
     * argument, false else.
     */
    public boolean checkPassword(String password);

    /**
     * Standard toString method to represent the object in a human
     * readable form.
     * @return The object in a human readable form.
     */
    public String toString();
 
    /** 
     * Changes the password from old to new if the old password is
     * correct, else an error message is printed.
     * @param oldPassword The current password.
     * @param newPassword The future password.
     */
    public void changePassword(String oldPassword, String newPassword);
}
