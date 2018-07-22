package org.upgrad.models;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name ="user_profile")
public class UserProfile implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    @Column(name="user_id")
    private String user_id;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="aboutme")
    private String aboutme;

    @Column(name="dob")
    private Date dob;

    @Column(name ="contactnumber")
    private int contactnumber;

    @Column(name="country")
    private String country;






    public UserProfile() { }

    public UserProfile(int id, String user_id, String firstname , String lastname , String aboutme , Date dob, int contactnumber , String Country) {
        this.id=id;
        this.user_id =user_id;
        this.firstname=firstname;
        this.lastname=lastname;
        this.aboutme=aboutme;
        this.dob=dob;
        this.contactnumber=contactnumber;
        this.country=country;

    }

    public int getId() { return id ;}

    public void setId(int id){this.id=id ;}

    public String getUser_id() { return user_id; }
    public void  setUser_id(String user_id ){this.user_id=user_id ;}

    public  String  getFirstName(){return firstname ;}
    public void  setFirstname(String firstname){this.firstname=firstname;}

    public  String getLastname() {return  lastname;}
    public void  setLastname(String lastname) {this.lastname=lastname;}

    public String  getAboutme() {return aboutme;}
    public void setAboutme(String aboutme) {this.aboutme=aboutme;}

    public Date getDob(){return dob; }
    public void setDob(Date dob) {this.dob=dob;}

    public int getContactnumber(){return contactnumber;}
    public void setContactnumber(int contactnumber){this.contactnumber=contactnumber;}

    public String getCountry(){return country ;}
    public void  setCountry(String country) {this.country=country;}


}
