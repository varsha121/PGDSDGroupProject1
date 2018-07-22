package org.upgrad.models;

import org.upgrad.services.CategoryService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
    @Table(name ="question")
    public class Question implements Serializable{


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name ="id")
        private int id;

        @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        private User user;

        @Column(name="content")
        private String content;

        @Column(name="date")
        private Date date;

        @Column(name="user_id")
        private int user_id;

        public Question() { }

        public Question(int id, String content,Date  date ,int user_id) {
            this.id=id;
            this.content=content;
            this.date=date;
            this.user_id =user_id;


        }

        public int getId() { return id ;}
        public void setId(int id){this.id=id ;}

        public  Date  getDate(){return date ;}
        public void  setDate(Date date){this.date=date;}

        public  String getContent() {return  content;}
        public void  setContent(String content) {this.content=content;}

        public int getUser_id() { return user_id; }
        public void  setUser_id(int user_id ){this.user_id=user_id ;}


        public void setUser(User user) {
        }

        public void setCategories(Set<CategoryService> categories) {
        }
    }

