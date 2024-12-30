package view;

import controller.Conn;

import java.sql.Connection;

public class Main {



    public static void main (String[]args){

        Connection bd = Conn.open();

        System.out.println("Realizamos las consultas");

        Conn.close();

    }
}
