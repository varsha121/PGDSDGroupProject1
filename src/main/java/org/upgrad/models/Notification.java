
package org.upgrad.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name ="notification")
public class Notification implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User user;

    @Column(name="message")
    private String message;

    @Column(name="date")
    private Date date;

    @Column(name="read")
    private boolean read=false;

    public Notification() {};

    public Notification(User user,String message ,Date date ,boolean read) {
        this.user=user;
        this.message=message;
        this.date=date;
        this.read=read;

    }

    public int getId() { return id ;}

    public void setId(int id){this.id=id ;}

    public User getUser() { return user; }
    public void  setUser(User  user ){this.user=user ;}

    public  String  getMessage(){return message ;}
    public void  setMessage(String message){this.message=message;}

    public  Date getDate() {return  date;}
    public void  setDate(Date date) {this.date=date;}

    public boolean getRead() {return read;}
    public void setRead(boolean read) {this.read=read;}



}
