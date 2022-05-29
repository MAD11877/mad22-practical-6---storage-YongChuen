package sg.edu.np.madexercise2;

import java.io.Serializable;
import java.util.Random;

public class User implements Serializable {
    String name;
    String description;
    int id;
    Boolean followed;

    public User(String name, String description, int id, Boolean followed) {
        this.name= name;
        this.description= description;
        this.id=id;
        this.followed= followed;
    }


    public void setID(int id) {
        this.id = id;
    }
    public int getID() {
        return this.id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
    public void setFollowed(int followed){
        if (followed == 1){
            this.followed = Boolean.TRUE;
        }
        else{
            this.followed = Boolean.FALSE;
        }
    }

    public Boolean getFollowed() {
        return this.followed;
    }

}


