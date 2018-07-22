package org.upgrad.models;

import javax.persistence.*;
import java.io.Serializable;


    @Entity
    @Table(name = "follow")
    public class Follow implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        private Question question;

        @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        private User user;
        @Column(name = "user_id")
        private int user_id;

        @Column(name = "category_id")
        private int category_id;


        public Follow() {
        }

        public Follow(int id, int user_id, int category_id) {
            this.id = id;
            this.user_id = user_id;
            this.category_id=category_id;

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getCategory_id() {
            return category_id;
        }
        public  void  setCategory_id(int category_id){
            this.category_id=category_id;
        }

    }

