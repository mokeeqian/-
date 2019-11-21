
/**
 * Abstract class
 */
abstract class Account implements AccountInterface{

    private String name;
    private String salutation;
    private String email;
    private String password;
    private boolean loggedIn;

    /**
     * Constructor
     * @param name
     * @param salutation
     * @param email
     * @param password
     * @param loggedIn
     */
    public Account(String name, String salutation, String email, String password, boolean loggedIn) {
        this.name = name;
        this.salutation = salutation;
        this.email = email;
        this.password = password;
        this.loggedIn = loggedIn;
    }

    /**
     * Constructor
     * @param name
     * @param salutation
     * @param email
     * @param password
     */
    public Account(String name, String salutation, String email, String password) {
        this.name = name;
        this.salutation = salutation;
        this.email = email;
        this.password = password;
    }


    /**
     * Getter for name
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for salutation
     * @return
     */
    @Override
    public String getSalutation() {
        return salutation;
    }

    /**
     * Setter for saluation
     * @param salutation
     */
    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    /**
     * Getter for email
     * @return
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for password
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password The new password.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for the loggedIn
     * @return
     */
    public boolean getLoggedIn() {
        return loggedIn;
    }

    /**
     * Setter for the loggedIn
     * @param loggedIn New value for the variable loggedIn
     */
    @Override
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", salutation='" + salutation + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}

