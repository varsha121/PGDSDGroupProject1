package org.upgrad.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;



    @Entity
    @Table(name ="comment")
    public class Comment implements Serializable{


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name ="id")
        private int id;

        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Answer answer;

        @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        private User user;

        @Column(name="content")
        private String content;

        @Column(name="date")
        private Date dob;

        @Column(name="user_id")
        private int user_id;

        @Column(name="answer_id")
        private  int answer_id;

        @Column(name="modifiedon")
        private Date modifiedon;

        public Comment() { }

        public Comment(int id, int user_id,String content ,Date dob ,int answer_id ,Date modifiedon) {
            this.id=id;
            this.user_id =user_id;
            this.content=content;
            this.dob=dob;
            this.answer_id=answer_id;
            this.modifiedon=modifiedon;


        }

        public int getId() { return id ;}

        public void setId(int id){this.id=id ;}

        public int getUser_id() { return user_id; }
        public void  setUser_id(int user_id ){this.user_id=user_id ;}

        public  String  getContent(){return content ;}
        public void  setContent(String content){this.content=content;}

        public  Date getDob() {return  dob;}
        public void  setDob(Date dob) {this.dob=dob;}

        public int  getAnswer_id() {return answer_id;}
        public void setAnswer_id(int answer_id) {this.answer_id=answer_id;}


        public Date getModifiedon(){return modifiedon;}
        public void setModifiedon(Date modifiedon){this.modifiedon=modifiedon;}


        public void setUser(User user) {

        }
        
    }

