package de.bnd.coding.sample.demo.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//TODO: Make userPassword md5 or something

// @Entity tells a JpaRepository that this corresponds to a database table
@Entity
// ... which is this table ;-)
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "firstname" )
    private String firstName;

    @Column( name = "lastname" )
    private String lastName;

    @Column( name = "username" , unique = true)
    private String userName;

    @Column( name = "userpassword" )
    private String userPassword;

    @Column( name = "zipcode" )
    private String zipCode = null;

    public UserEntity() {
    }

    public UserEntity(String userName, String userPassword, String firstName, String lastName) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

/*    public UserEntity(String userName, String userPassword, String firstName, String lastName, String zipCode) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipCode = zipCode;
    }
 */

    public String getZipCode() { return zipCode; }

    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserPassword() { return userPassword; }

    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
