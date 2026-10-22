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
        this.servidor = "";
        this.banco = "";
        this.usuario = "";
        this.senha = "";
    }
    
public boolean conectar(){
    try {
        // Adicionada a porta :3306 e o parâmetro de SSL desativado
        String url = "jdbc:mysql://" + this.servidor + ":3306/" + this.banco + 
                     "?useSSL=false&allowPublicKeyRetrieval=true";
        this.conexao = DriverManager.getConnection(url, this.usuario, this.senha);
        return true;
    } catch(SQLException ex){
        throw new RuntimeException(ex);
    }
}
    
    public Connection getConnection(){
        return conexao;
    }
    
}
