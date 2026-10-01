/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

  
   
package dao;

   import java.sql.Connection;
   import java.sql.DriverManager;
   import java.sql.SQLException;
   
   
public class ConexaoBanco {
    private String servidor;
    private String banco;
    private String usuario;
    private String senha;
    private Connection conexao;
    
    public ConexaoBanco(){
        this.servidor = "177.220.142.20";
        this.banco = "grupo04_igor";
        this.usuario = "grupo06";
        this.senha = "woyOlFfNNOdBOCE9@";
    }
    
    public boolean conectar(){
        try
        {
            this.conexao = DriverManager.getConnection("jdbc:mysql://"+this.servidor+"/"+this.banco,this.usuario,this.senha);
            return true;
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
    
    public Connection getConnection(){
        return conexao;
    }
    
}
