/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.beans.PersistenceDelegate;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        String sql = "INSERT INTO produtos (nome, valor,status) VALUES (?,?,?)";
                
        conn = new conectaDAO().connectDB();
        
         try
              {
                 prep = conn.prepareStatement(sql);
                 prep.setString(1,produto.getNome());
                 prep.setInt(2,produto.getValor());
                 prep.setString(3,produto.getStatus());
                 
                 prep.execute();
                 prep.close();
                 
                 JOptionPane.showMessageDialog(null,"Cadastro executado com exito");
              }
        catch (Exception e)
              {
                  JOptionPane.showMessageDialog(null, e);
              }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
   conn = new conectaDAO().connectDB();
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    String sql = "SELECT * FROM produtos";

    try {
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next())
        {
            ProdutosDTO prod = new ProdutosDTO();
            prod.setId(resultset.getInt("id"));
            prod.setNome(resultset.getString("nome"));
            prod.setValor(resultset.getInt("valor"));
            prod.setStatus(resultset.getString("status"));
            
           

            listagem.add(prod);
        }

    } 
    catch (SQLException erro)
    {
        System.out.println("Erro ao listar: " + erro.getMessage());
    }

        return listagem;
}
    public void venderProduto (int idProduto )
    {
        
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
          try
            {
              conn = new conectaDAO().connectDB();
              prep = conn.prepareStatement(sql);
                
              prep.setInt(1, idProduto);
              
              prep.executeUpdate();
              prep.close();
              
              JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
              
            }
          catch (SQLException e)
            {
                System.out.println("Erro ao vender produto" + e.getMessage());
                      
            }
        
    }
    
    public ArrayList<ProdutosDTO> produtosVendidos()
    {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        
        try 
        {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while ( resultset.next())
            {
                ProdutosDTO prod = new ProdutosDTO();
                prod.setId(resultset.getInt("id"));
                prod.setNome(resultset.getString("nome"));
                prod.setValor(resultset.getInt("valor"));
                prod.setStatus(resultset.getString("status"));
                
                listagem.add(prod);
                
            }
            resultset.close();
            prep.close();
            
            
        }
        catch (SQLException e) 
        {
            System.out.println("Erro ao listar vendidos" + e.getMessage());
        }
        return listagem;
    }
    }
    
    
    
        


