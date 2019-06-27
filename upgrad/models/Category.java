package org.upgrad.models;


import org.upgrad.services.CategoryService;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name ="category")
public class Category implements Serializable, CategoryService {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    public Category() { }

    public Category(int id, String  title,String description  ) {
        this.id=id;
        this.title = title;
        this.description=description;

    }

    public int getId() { return id ;}

    public void setId(int id){this.id=id ;}

    public String getTitle() { return title; }
    public void  setTitle(String title ){this.title=title ;}
    public  String  getDescription(){return description ;}
    public void  setDescription(String description){this.description=description ;}


}



