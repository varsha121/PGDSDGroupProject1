
package org.upgrad.models;
        import javax.persistence.*;
        import java.io.Serializable;


@Entity
@Table(name = "likes")
public class Like implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  Answer answer;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "answer_id")
    private int answer_id;


    public Like() {
    }

    public Like(int id, int user_id, int answer_id) {
        this.id = id;
        this.user_id = user_id;
        this.answer_id = answer_id;

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

    public int getAnswer_id() {
        return answer_id;
    }
    public  void  setAnswer_id(int answer_id){
        this.answer_id=answer_id;
    }

}
