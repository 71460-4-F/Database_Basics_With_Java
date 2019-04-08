/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.quixada.dao;

/**
 *
 * @author Tiago
 */
import ufc.factory.ConnectionFactory;
import br.ufc.quixada.entity.Funcionario;
import java.sql.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
public class FuncionarioDAO { 
    private final Connection connection;
    
    String cpf;
    String pnome;
    String minicial;
    String unome;
    Date datanasc;
    String endereco;
    String sexo;
    double salario;
    
    public FuncionarioDAO(){ 
        this.connection = new ConnectionFactory().getConnection();
    } 
    public void adiciona(Funcionario funcionario){ 
        String sql = "INSERT INTO funcionario VALUES(?,?,?,?,?,?,?,?)";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getPnome());
            stmt.setString(3, funcionario.getMinicial());
            stmt.setString(4, funcionario.getUnome());
            stmt.setDate(5, (java.sql.Date) funcionario.getDatanasc());
            stmt.setString(6, funcionario.getEndereco());
            stmt.setString(7, funcionario.getSexo());
            stmt.setDouble(8, funcionario.getSalario());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
        
    } 
    
    public void atualizar(Funcionario funcionario) {
    String sql = "UPDATE funcionario SET pnome=?, minicial=?, unome=?, datanasc=?, endereco=?, sexo=?, salario=? WHERE cpf =?";
    try {
            PreparedStatement astmt = connection.prepareStatement(sql);
            //pstmt.setString(1, funcionario.getCpf());
            astmt.setString(1, funcionario.getPnome());
            astmt.setString(2, funcionario.getMinicial());
            astmt.setString(3, funcionario.getUnome());
            astmt.setDate(4, (java.sql.Date) funcionario.getDatanasc());
            astmt.setString(5, funcionario.getEndereco());
            astmt.setString(6, funcionario.getSexo());
            astmt.setDouble(7, funcionario.getSalario());
            astmt.setString(8, funcionario.getCpf());
            astmt.execute();
            //pstmt.executeUpdate();
            astmt.close();
         
        //JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");

     }   catch (SQLException erro) {
       // JOptionPane.showMessageDialog(null, "Erro na Alteração" + erro.getMessage());

        }
    }
    
     public void delete(Funcionario deletefuncionario) {
         String sql = "DELETE FROM funcionario WHERE cpf =?;";
          try {
             try (PreparedStatement dstmt = connection.prepareStatement(sql)) {
                 dstmt.setString(1, deletefuncionario.getCpf());
                 dstmt.execute();
             }
             
         } catch (SQLException erro) {
             JOptionPane.showMessageDialog(null, "Erro ao tentar deletar!" + erro.getMessage());
         }
 
     }
    
     public void buscar(Funcionario buscarfuncionario){
          //String sql = "SELECT * FROM FUNCIONARIO WHERE cpf=?";
     

          try {
             String sql = "SELECT * FROM FUNCIONARIO WHERE cpf=?";
             PreparedStatement bstmt = connection.prepareStatement(sql);
             bstmt.setString(1, buscarfuncionario.getCpf());
             ResultSet resultado = bstmt.executeQuery();
             
             
             while (resultado.next()){
                 String cpf = resultado.getString("cpf");
		 String pnome = resultado.getString("pnome");
                 String minicial = resultado.getString("minicial");
		 String unome = resultado.getString("unome");
                 Date datanasc = resultado.getDate("datanasc");
                 String endereco = resultado.getString("endereco");
                 String sexo = resultado.getString("sexo");
                 Double salario = resultado.getDouble("salario");
                 
		 System.out.println(cpf+"  "+pnome+"  "+minicial+"  "+unome+"  "+datanasc+"  "+endereco+"  "+
                         sexo+"  "+salario);	
			}
             bstmt.close();
             
         } catch (SQLException erro) {
             System.out.println("Falha na conexão!");
         }
          
     }
    
    

}