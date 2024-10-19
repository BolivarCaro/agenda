package model;


import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class Cliente extends DbCliente{
    public int id;
    public String nombre;
    public String apellido;
    public String telefono;
    public String email;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String apellido, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public boolean insert() {
        Connection connection; 
        try{
            Class.forName(driver);
            String query = "INSERT INTO contactos VALUES(null,?,?,?,?)" ;
            connection = DriverManager.getConnection(url, user, password);
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);
            sentencia.setString(3, telefono);
            sentencia.setString(4, email);
            
            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;
            
        }catch (Exception e){
            System.out.println("Error al insertar en la base de datos" + e.getMessage());
            return false;
        }
        
        
    }
    
    public ArrayList<Cliente> Select(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM contactos";
            PreparedStatement sentencia = connection.prepareStatement(query);
            ResultSet rs = sentencia.executeQuery();
            ArrayList<Cliente> listaclientes = new ArrayList<Cliente>();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.id = rs.getInt(1);
                cliente.nombre = rs.getString(2);
                cliente.apellido = rs.getString(3);
                cliente.telefono = rs.getString(4);
                cliente.email = rs.getString(5);
                listaclientes.add(cliente);
                
            }
            
            return listaclientes;
        } catch (Exception e) {
            System.out.println("Error al listar los clientes " + " " + e.getMessage());
            return null;
        } 
    }
    
    public boolean Update(){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String query = "UPDATE contactos SET nombre = ?, apellido = ?,"
                    + "telefono = ?, email = ? WHERE id = ?";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);
            sentencia.setString(3, telefono);
            sentencia.setString(4, email);
            sentencia.setInt(5, id);
            int filasAfectadas = sentencia.executeUpdate();
            connection.close();
            
            return filasAfectadas > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar el registro" + " " + e.toString());
            return false;
        }
    
    }
    
    public Cliente find(int id){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM contactos WHERE id = ?";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setInt(1, id);
            
            ResultSet rs = sentencia.executeQuery();
            Cliente cliente = new Cliente();
            if(rs.next()){
                cliente.id = rs.getInt(1);
                cliente.nombre = rs.getString(2);
                cliente.apellido = rs.getString(3);
                cliente.telefono = rs.getString(4);
                cliente.email = rs.getString(5);
            }
            else{
                cliente = null;
                
            }
            connection.close();
            return cliente;
        } catch (Exception e) {
            System.out.println("Error" + " " + e.toString());
            return null;
        }
    }
    
    public boolean delete(int id){
        Connection connection;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM contactos WHERE id =?";
            PreparedStatement sentencia = connection.prepareStatement(query);
            sentencia.setInt(1, id);
            int filasAfectadas = sentencia.executeUpdate();
            connection.close();
            if(filasAfectadas > 0){
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error " + " " + e.toString());
            return false;
        }
    }
}
       




