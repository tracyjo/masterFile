package com.company;
        import org.hibernate.SessionFactory;
        import org.hibernate.cfg.Configuration;
        import org.hibernate.service.ServiceRegistry;
        import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by josephtracy on 5/23/16.
 */
public class DatabaseConnect {

    private static SessionFactory sessionFactory;


    static {
        Configuration config = new Configuration();

        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

        //change the next line of code to match your MySQL URL and port

        config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:8889/javadb?useLegacyDatetimeCode=false&serverTimezone=US/Mountain");


        //change the next two lines of code to match your MySQL user name and password.

        config.setProperty("hibernate.connection.username", "root");

        config.setProperty("hibernate.connection.password", "root");

        //change the pool size to reflect how many users you expect your application to have initially

        config.setProperty("hibernate.connection.pool_size", "1");

        config.setProperty("hibernate.connection.autocommit", "true");

        config.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");

		/*
		 * un-comment the next line of code if you want to be able to drop and recreate tables for your data classes listed below.  This is generally a bad idea for security reasons.
		 */
        //
        //jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

        //config.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        config.setProperty("hibernate.show_sql", "true");

        config.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");

        config.setProperty("hibernate.current_session_context_class", "thread");

		/*
		 *  Add your classes here that you want to match your database tables
		 *  The example has a User and a PhoneNumber class.
		 */

        config.addAnnotatedClass(Character.class);

      //  config.addAnnotatedClass(Item.class);
/*
 * There have been several changes to the Hibernate libraries.
 * Please uncomment the source code for the version of Hibernate you are using.
*/
         /*Hibernate 4.3 - 5.x */ ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        /*Hibernate 3.x - 4.2*/ //ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
       // System.out.println("Hello");
        sessionFactory = config.buildSessionFactory(serviceRegistry);
       // System.out.println("There");
    }

    public DatabaseConnect() {

    }



    public static SessionFactory getSessionFactory() {

        return sessionFactory;

    }
 //   public SessionFactory getSessionFactory() {
//
  //      return sessionFactory;
//  }

}
