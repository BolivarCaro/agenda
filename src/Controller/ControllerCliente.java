
package Controller;

import java.util.ArrayList;
import model.Cliente;


public class ControllerCliente {
    public boolean insertCliente(String nombre, String apellido, String telefono, 
            String email){
        Cliente cliente = new Cliente();
        
        cliente.nombre = nombre;
        cliente.apellido = apellido;
        cliente.telefono = telefono;
        cliente.email = email;
        
        return cliente.insert();
    }
    public boolean UpdateCliente(int id, String nombre, String apellido, String telefono,
            String email){
        Cliente cliente = new Cliente();
        cliente.id = id;
        cliente.nombre = nombre;
        cliente.apellido = apellido;
        cliente.telefono = telefono;
        cliente.email = email;
        return cliente.Update();
    
    }
    
    public Cliente findCliente(int id){
        Cliente cliente = new Cliente();
        return cliente.find(id);
        
    }
    
    public boolean deleteCliente(int id){
        Cliente cliente = new Cliente();
        return cliente.delete(id);
    }
    
    public ArrayList<Cliente>SelectCliente(){
        Cliente cliente = new Cliente();
        return cliente.Select();
      
    }
    
    
}
