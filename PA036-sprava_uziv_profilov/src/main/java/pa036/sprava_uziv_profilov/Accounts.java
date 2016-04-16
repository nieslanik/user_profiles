/**
 * 
 * Interface for handling user's accounts - for log in, log out, status and number of logins.
 * 
 */

package pa036.sprava_uziv_profilov;

/**
 *
 * @author akaren
 */
public interface Accounts {
    
   /**
   * This method is used to log in.
   *    
   * @param username 
   * @param password  
   * @return Boolean This returns success of log in.
   */
    Boolean login(String username, String password);
    
   /**
   * This method is used to log out.
   *    
   * @param username 
   * @return Boolean This returns success of log out.
   */
    Boolean logout(String username);
        
   /**
   * This method is used to register user.
   * @param username 
   * @param password
   * @param employee 
   * @return Boolean This returns success of register.
   */
    Boolean register(String username, String password, Boolean employee);
    
   /**
   * This method is used to check user's status.
   * @param username  
   * @return int This returns user's status.
   */
    int userStatus(String username);
    
    /**
   * This method is used to count number of login users. 
   * @return int This returns count of login users.
   */  
    int numberOfLogin();
}
