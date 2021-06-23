
package censusapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class CensusApplication {
    public static Session session;
    public static Connection con;
    public static void main(String args[])
    {
        SessionFactory factory = new Configuration().configure("hibernate_cfg.xml").
                addAnnotatedClass(Member.class).buildSessionFactory();
    	
         session = factory.openSession();


        try {
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/mydb", "postgres", "1234");


        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }


        Integer choice;
        Scanner sc = new Scanner(System.in);
        CliHelper helper = new CliHelper();

        System.out.println("CENSUS\n");







        while(true) {
            System.out.println("\n1.Add Member\n2.Get Members");

            System.out.print("\nPlease enter your choice : ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter number of family members : ");
                    Integer count = sc.nextInt();

                    for (Integer i = 1; i <= count; i++) {
                        if(i==1)
                        helper.addMember(true);
                        else
                            helper.addMember(false);
                    }
                    break;

                case 2:
                    helper.getMembers();
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
            break;
        }
    	    factory.close();
    }
}
