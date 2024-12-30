package com.mycompany.supermercado.model;

import java.util.List;
import java.util.ArrayList;

public class ListaSupermercado {
    
    private static List<Supermercado> lista;
    
    private ListaSupermercado(){}
    
    public static List<Supermercado> getInstance(){
        if(lista == null){
            lista = new ArrayList<>();
        }
        
        return lista;
    }
    
}
