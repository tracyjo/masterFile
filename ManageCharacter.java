package com.company;

import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by josephtracy on 5/2/16.
 */
public class ManageCharacter {
        private static SessionFactory factory;
        public static void main(String[] args) {
            try{
                factory = (SessionFactory) new Configuration().configure();
            }catch (Throwable ex) {
                System.err.println("Failed to create sessionFactory object." + ex);
                throw new ExceptionInInitializerError(ex);
            }
            ManageCharacter ME = new ManageCharacter();

      /* Add few employee records in database */
            Integer empID1 = ME.addCharacter(1,"Zara", "Ali", 1000);
            Integer empID2 = ME.addCharacter(2,"Daisy", "Das", 5000);
            Integer empID3 = ME.addCharacter(3,"John", "Paul", 10000);

      /* List down all the employees */
            ME.listCharacter();

      /* Update employee's records */
            ME.updateCharacter(empID1, 5000);

      /* Delete an employee from the database */
            ME.deleteCharacter(empID2);

      /* List down new list of the employees */
            ME.listCharacter();
        }
        /* Method to CREATE an employee in the database */
        public Integer addCharacter(int characterID, String firstName, String lastName, int age){
            Session session = factory.openSession();
            Transaction tx = null;
            Integer CharacterID = characterID;
            try{
                tx = session.beginTransaction();
                Character character = new Character(characterID, firstName, lastName, age);
                characterID = (Integer) session.save(character);
                tx.commit();
            }catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
            }finally {
                session.close();
            }
            return characterID;
        }
        /* Method to  READ all the employees */
        public void listCharacter( ){
            Session session = factory.openSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                List character = session.createQuery("FROM game_character").list();
                for (Iterator iterator =
                     character.iterator(); iterator.hasNext();){
                    Character character1 = (Character) iterator.next();
                    System.out.print("First Name: " + character1.getFirstName());
                    System.out.print("  Last Name: " + character1.getLastName());
                    System.out.println("  Age: " + character1.getAge());
                }
                tx.commit();
            }catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
            }finally {
                session.close();
            }
        }
        /* Method to UPDATE salary for an employee */
        public void updateCharacter(Integer CharacterID, int age ){
            Session session = factory.openSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                Character character =
                        (Character)session.get(Character.class, CharacterID);
                character.setAge( age );
                session.update(character);
                tx.commit();
            }catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
            }finally {
                session.close();
            }
        }
        /* Method to DELETE an employee from the records */
        public void deleteCharacter(Integer CharacterID){
            Session session = factory.openSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                Character character =
                        (Character)session.get(Character.class, CharacterID);
                session.delete(character);
                tx.commit();
            }catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
            }finally {
                session.close();
            }
        }
    }

