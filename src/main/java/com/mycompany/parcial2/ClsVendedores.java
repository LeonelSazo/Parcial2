/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author Leonel
 */
public class ClsVendedores {
    
    public static void main(String[] args) {
//        consulniv(4852);
//        consulventas();
//        String Nombre = "Leonel";
//        Actualizar(4852,Nombre);
//        Eliminar(4852);

boolean salir=false; 
int opcion =0; int numniv=0; int opceliminar =0;
String Nombre="";
Scanner Ls = new Scanner(System.in);

//Se crea el menu
        while(!salir){
        System.out.println("________Menu________");
        System.out.println("1.  Consulta por NIV");
        System.out.println("2. Listar vendedores y ventas totales por mes");
        System.out.println("3. Eliminar registro");
        System.out.println("4. Actualizar registro");
        System.out.println("5. Salir");
        System.out.println("Elige lo que deseas ver");
        opcion = Ls.nextInt();
        
        switch (opcion){
            case 1:
                System.out.println("Ingrese el numero de identificación del vendedor");
                numniv = Ls.nextInt();
                consulniv(numniv);
                break;
                case 2:
                consulventas();
                
                break;
            case 3:
                System.out.println("Ingrese el numero de identificación del vendedor que desea eliminar");
                numniv = Ls.nextInt();
                System.out.println("Esta seguro que desea eliminar el registro \n Opciones \n 1. Si \n 2. No \n Ingrese el numero de opcion");
                opceliminar = Ls.nextInt();
                if (opceliminar ==1){
                    Eliminar(numniv);
                }else {
                    System.out.println("De acuerdo");
                }
                
                break;
            case 4:
                System.out.println("Ingrese el numero de identificación del vendedor");
                numniv = Ls.nextInt();
                System.out.println("Ingrese el nombre a actualizar");
                Nombre = Ls.nextLine();
                System.out.println(" ");
                Actualizar(numniv, Nombre);
                
                break;
            case 5:
                salir=true;
            default:
                System.out.println("La opcion no es valida");break;
                
        }
        System.out.println("------FIN-----");
     }
    }
        
    
    
    public static void consulniv(int niv){
        int suma=0;
    //Se crea la conexion a la base de datos
        String url = "jdbc:mysql://localhost:3306/parcial2?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
        try {
            //Se crea el objeto conexion
            Connection conexion = DriverManager.getConnection(url,"root","");
            //Se crea el objeto statement
            Statement sentencia = conexion.createStatement();
            //Se crea la instruccion
            String sql = "SELECT * FROM tb_vendedores where niv =   "+niv;
            // Se ejecuta el query
            ResultSet resultado = sentencia.executeQuery(sql);
            
            // se imprimen los resultados
            while(resultado.next()){
                System.out.println("Correlativo = "+resultado.getInt(1));
                System.out.println("NIV ="+resultado.getInt(2));
                System.out.println("Nombre = "+resultado.getString(3));
                System.out.println("Enero = Q."+resultado.getInt(4));
                System.out.println("Febrero = Q."+resultado.getInt(5));
                System.out.println("Marzo = Q."+resultado.getInt(6));
                System.out.println("Abril = Q."+resultado.getInt(7));
                System.out.println("Mayo = Q."+resultado.getInt(8));
                System.out.println("Junio = Q."+resultado.getInt(9));
                for (int i =4; i < 10; i++){
                            suma = (resultado.getInt(i))+suma;
                        }
                System.out.println("Total ventas = Q."+suma+"\n");
            }
        } catch (SQLException ex) {
            System.out.println("Hubo clavo:"+ex.getMessage());
        }
    
    }
    
    public static void consulventas(){
        int sumene=0; int sumfeb=0; int sumarz=0; int sumab=0; int sumayo=0; int sumjun=0;
    //Se crea la conexion a la base de datos
        String url = "jdbc:mysql://localhost:3306/parcial2?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
        try {
            //Se crea el objeto conexion
            Connection conexion = DriverManager.getConnection(url,"root","Leonel4585sazo");
            //Se crea el objeto statement
            Statement sentencia = conexion.createStatement();
            //Se crea la instruccion
            String sql = "SELECT * FROM tb_vendedores  ";
            // Se ejecuta el query
            ResultSet resultado = sentencia.executeQuery(sql);
            
            // se imprimen los resultados
            System.out.println("Vendedores");
            while(resultado.next()){
                System.out.println(resultado.getInt(1)+" "+resultado.getString(3));

                sumene=(sumene +(resultado.getInt(4)));
                sumfeb=(sumfeb +(resultado.getInt(5)));
                sumarz=(sumarz +(resultado.getInt(6)));
                sumab=(sumab +(resultado.getInt(7)));
                sumayo=(sumayo +(resultado.getInt(8)));
                sumjun=(sumjun +(resultado.getInt(9)));
            }   
            System.out.println("\n Total ventas mensuales." );
            System.out.println("ENERO:   Q."+sumene );
            System.out.println("FEBRERO: Q."+sumfeb);
            System.out.println("MARZO: Q."+sumarz);
            System.out.println("ABRIL: Q."+sumab);
            System.out.println("MAYO: Q."+sumayo);
            System.out.println("JUNIO: Q."+sumjun);
        } catch (SQLException ex) {
            System.out.println("Hubo clavo:"+ex.getMessage());
        }
    
    }
    
    public static void Eliminar(int niv){
 
    //Se crea la conexion a la base de datos
        String url = "jdbc:mysql://localhost:3306/parcial2?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
        try {
            //Se crea el objeto conexion
            Connection conexion = DriverManager.getConnection(url,"root","Leonel4585sazo");
            //Se crea el objeto statement
            Statement sentencia = conexion.createStatement();
            //Se crea la instruccion
            String sql = "delete from tb_vendedores where niv = "+niv;
            System.out.println(sql);
            // Se ejecuta el query
            sentencia.execute(sql);
            
            
        } catch (SQLException ex) {
            System.out.println("Hubo clavo:"+ex.getMessage());
        }
    
    }
    
    public static void Actualizar(int niv, String nombre){
 
    //se crea la conexion a la base de datos
        String url = "jdbc:mysql://localhost:3306/parcial2?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
        try {
            //Se crea el objeto conexion
            Connection conexion = DriverManager.getConnection(url,"root","Leonel4585sazo");
            //Se crea el objeto statement
            Statement sentencia = conexion.createStatement();
            //Se crea la instruccion
            String sql = "Update tb_vendedores set nombre = '"+nombre+ "' where niv = "+niv;
            System.out.println(sql);
            // Se ejecuta el query
            sentencia.execute(sql);
            
            
        } catch (SQLException ex) {
            System.out.println("Hubo clavo:"+ex.getMessage());
        }
    
    }
    
    
}
