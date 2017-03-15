/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;


import java.util.*;

/**
 *
 * @author gil
 */
public class Querys {
    
    private Map<String,List<String>> queryUser;
    private Map<String,List<String>> recomendacao;
    private final String TOPCLIENTES = "Select top 10 NomeCompleto,Email, COUNT(*) as TotalCompas "
                + "from FactTable inner join Cliente on FactTable.IdCliente = Cliente.id "
                + "group by NomeCompleto,Email order by 3 desc";
    
    private final String TOP10CLIENTESANO ="select top 10  NomeCompleto, Email, Ano, COUNT(*) As TotalComprasPorAno "
                + "from Cliente as C INNER JOIN FactTable ON C.id = IdCliente "
                + "INNER JOIN DataOrdem AS DO ON IdDataOrdem = DO.id "
                + "group by NomeCompleto,Email,Ano "
                + "order by 4 desc";
    
    public static final String CLIENTE = "CLIENTE";
    
    
    
    
    public Querys() {
    
        this.queryUser = new HashMap<>();
        this.recomendacao = new HashMap<>();
        
        List<String> listaClientes = new ArrayList<>();
        List<String> listaClientesRecomendacao = new ArrayList<>();
        
        listaClientes.add(TOPCLIENTES);
        
        listaClientesRecomendacao.add(TOP10CLIENTESANO);
        
        this.queryUser.put(CLIENTE, listaClientes);
        this.recomendacao.put(CLIENTE, listaClientesRecomendacao);
       
    }

    public Map<String,List<String>> getQueryUser() {
        return queryUser;
    }

    public void setQueryUser(Map<String,List<String>> queryUser) {
        this.queryUser = queryUser;
    }

    public Map<String,List<String>> getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(Map<String,List<String>> recomendacao) {
        this.recomendacao = recomendacao;
    }
    
    
    
    
    
    
    
    
    
    
    
}
