package com.company;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

/**
 * Created by josephtracy on 5/23/16.
 */

public class hib_run {
        private List<Character> Characters;
        private DatabaseConnect theConnect;


        public hib_run(){

            theConnect = new DatabaseConnect();

        }

/*
    TimeZone timeZone = TimeZone.getTimeZone("US/Mountain"); // e.g. "Europe/Rome"
    TimeZone.setDefault(timeZone);
  */
        public static void main(String[] args){


            Character man1 = new Character(1,"John", "Smith", 23);
            Character man2 = new Character(2,"Jane", "Smith", 23);

            ArrayList characterList = new ArrayList();
            characterList.add(man1);
            characterList.add(man2);

            hib_run charhib = new hib_run();
            charhib.deleteAddedCharacters();
            charhib.addNewCharacters();
            charhib.showAllCharacters();
            charhib.modifyCharacter();
     //       charhib.addSharedItem();
            charhib.deleteAddedCharacters();

        }

        /*
         * show how to add records to the database
         */
        private void addNewCharacters() {

            Session session = theConnect.getSessionFactory().getCurrentSession();
            /*
         * all database interactions in Hibernate are required to be inside a transaction.
         */
            Transaction transaction = session.beginTransaction();
        /*
         * create some User instances.
         */
            Character objCharacter = new Character(1,"aFirstName","aLastName",23);
            Character aCharacter1  = new Character(2,"lee","barney",21);


        /*
         * save each instance as a record in the database
         */
            session.save(objCharacter);
            session.save(aCharacter1);
            transaction.commit();
        /*
         * prove that the User instances were added to the database and that
         * the instances were each updated with a database generated id.
         */
            System.out.println("aUser generated ID is: " + objCharacter.getCharacterID());
            System.out.println("anotherUser generated ID is: " + aCharacter1.getCharacterID());
        }

        /*
         * show how to get a collection of type List containing all of the records in the app_user table
         */
        private void showAllCharacters() {
            Session session = theConnect.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
        /*
         * execute a HQL query against the database.  HQL is NOT SQL.  It is object based.
         */
            Query allCharacterQuery = session.createQuery("select c from Character as c order by c.characterID ");
        /*
         * get a list of User instances based on what was found in the database tables.
         */
            Characters = allCharacterQuery.list();
            // System.out.println("num characters: "+ Characters.size());


        /*
         * iterate over each User instance returned by the query and found in the list.
         */
            Iterator<Character> iterator = Characters.iterator();
            while(iterator.hasNext()) {
                Character element = iterator.next();
                System.out.println(element.toString());
                System.out.println("num of Characters: "+ Characters.size());
            }
            transaction.commit();
        }

        /*
         * show how to modify a database record
         */
        private void modifyCharacter() {

            Session session = theConnect.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
        /*
         * get a single User instance from the database.
         */
            Query singleUserQuery = session.createQuery("select c from Character as c where c.firstName='lee'");
            Character aCharacter1 = (Character)singleUserQuery.uniqueResult();
        /*
         * change the user name for the Java instance
         */
            aCharacter1.setFirstName("john");
        /*
         * call the session merge method for the User instance in question.  This tells the database that the instance is ready to be permanently stored.
         */
            session.merge(aCharacter1);

        /*
         * call the transaction commit method.  This tells the database that the changes are ready to be permanently stored.
         */
            transaction.commit();
        /*
         * permanently store the changes into the database tables.
         */
            showAllCharacters();
        }

     /*   private void addSharedItem() {
            Session session = theConnect.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
        /*
         * get two User instances from the database using HQL.  This is NOT SQL.  It is object based.
         *
            Query joshuaQuery = session.createQuery("from Character as c where c.firstName='john'");
            Character joshuaUser = (Character)joshuaQuery.uniqueResult();

            Query aNameQuery = session.createQuery("select character_id from game_character as c where c.first_name='aName'");
            Character objCharacter = (Character)aNameQuery.uniqueResult();

        /*
         * create a Item instance
         *
            Item sharedItem = new Item(1,"Mana Potion","Potion");


       /*
        * add the shared Item to joshuaUser
        *

            Set<Item> joshuaItems = joshuaUser.getItems();
            joshuaItems.add(sharedItem);
       /*
        * set the single phone number to be used by more than one User
        *
            Set<Item> aItem = objCharacter.getItems();
            aItem.add(sharedItem);
       /*
        * inform the database that the phone number should be ready for permanent storage.
        *
            session.save(sharedItem);
       /*
        * inform the database that the modified User instances should be ready for permanent storage.
        *
            session.merge(joshuaUser);
            session.merge(objCharacter);
       /*
        * permanently store the changes into the database tables.
        *
            transaction.commit();
       /*
        * show that the database was updated by printing out all of the User instances created by a HQL query
        *
            showAllCharacters();

        }*/
        private void deleteAddedCharacters() {
            Session session = theConnect.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Query allCharacterQuery = session.createQuery("select c from Character as c order by c.characterID ");
            Characters = allCharacterQuery.list();

            int numCharacters = Characters.size();
            System.out.println("Characters count: " + numCharacters);
            for(int i = 0; i < numCharacters; i++){
                System.out.println("deleting user "+ Characters.get(i).getFirstName() + ", " + Characters.get(i).getLastName());
                session.delete(Characters.get(i));
            }
            transaction.commit();
        /*
          * at this point the records have been removed from the database but still exist in our class list attribute.
          * Do not store lists retrieved from the database since they will be out of synch with the database table from which they come.
          * This example shows that you should not store retrieved lists.
          */
            System.out.println(Characters);
            Characters.clear();
        /*
          * now the Java instances are also gone and the database is back to its original state so the example application can be run again.
          */
            System.out.println(Characters);
        }
    }

