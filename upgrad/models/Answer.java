package org.upgrad.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


    @Entity
    @Table(name ="user_profile")
    public class Answer implements Serializable{



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name ="id")
        private int id;

        @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        private User user;
        @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
        private Question question;



        @Column(name="ans")
        private String ans;

        @Column(name="Date")
        private Date date;

        @Column(name="user_id")
        private int user_id;

        @Column(name="question_id")
        private int question_id;

        @Column(name="modifiedon")
        private Date modifiedon ;







        public Answer() { }

        public Answer(int id, String ans,Date date,int user_id,int question_id,Date modifiedon) {
            this.id=id;
            this.user_id =user_id;
            this.ans=ans;
            this.date=date;
            this.question_id=question_id;
            this.modifiedon=modifiedon;


        }

        public int getId() { return id ;}

        public void setId(int id){this.id=id ;}

        public int getUser_id() { return user_id; }
        public void  setUser_id(int user_id ){this.user_id=user_id ;}

        public  String  getAns(){return ans ;}
        public void  setAns(String Ans){this.ans=ans;}

        public  Date date() {return  date;}
        public void  setDate(Date date) {this.date=date;}

        public int  getQuestion_id() {return question_id;}
        public void setQuestion_id(int aboutme) {this.question_id=question_id;}

        public Date getModifiedon(){return modifiedon; }
        public void setModifiedon(Date modifiedon) {this.modifiedon=modifiedon;}


        public void setUser(User user) {
        }
    }


