package com.company;
        import javax.persistence.*;
        import java.io.Serializable;
        import java.util.HashSet;
        import java.util.Set;

/**
 * Created by josephtracy on 4/25/16.
 */
@Entity
@Table(name = "game_character")
public class Character implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="character_id")
    private Integer characterID;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age")
    private Integer age;


 /*   @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="character_inventory",
            joinColumns = { @JoinColumn( name="character_id") },
            inverseJoinColumns = @JoinColumn( name="inventory_id")
    )
    */
   // private Set<Item> items;

public Character() {

}

    public Character(int aCharacterID, String aFirstName, String aLastName, int anAge) {
        this.characterID = aCharacterID;
        this.firstName = aFirstName;
        this.lastName = aLastName;
        this.age = anAge;

    }




    public int getCharacterID(){return this.characterID;}

    public void setCharacterID(int characterId) {this.characterID = characterId;}

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

  /*  public Set<Item> getItems() {
        return this.items;
    }
*/

    @Override
    public String toString() {
        return "Character{" +
                "characterId= " + characterID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

        public String toStringSimple() {
            return "Character = " + firstName + " " + lastName + ", " + age;
    }


}