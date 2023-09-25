package com.example.remake.services;


import com.example.remake.entities.Cliente;
import com.example.remake.repositories.IARepositoriCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {


    @Autowired
    private IARepositoriCliente iaRepositoriCliente;


    public Cliente saveCliente(Cliente cliente){
        iaRepositoriCliente.save(cliente);
        return cliente;
    }
    public List<Cliente> listClientes(){
        return  iaRepositoriCliente.findAll();
    }
    public boolean existsByIdCliente(Long idCliente) {
        return iaRepositoriCliente.existsById(Math.toIntExact(idCliente));
    }

    public void updateCliente(Cliente updatedCliente) {
        // Busca el cliente existente en la base de datos
        Cliente existingCliente = iaRepositoriCliente.findByIdCliente(updatedCliente.getIdCliente());

        if (existingCliente != null) {
            // Actualiza los campos del cliente existente con los datos del cliente actualizado
            existingCliente.setNombre(updatedCliente.getNombre());
            existingCliente.setApellido(updatedCliente.getApellido());
            existingCliente.setUsuario(updatedCliente.getUsuario());
            // Actualiza otros campos...
            // Guarda los cambios en la base de datos
            iaRepositoriCliente.save(existingCliente);
        }
    }


    public Cliente authenticate(String username, String password) {
        Cliente cliente = iaRepositoriCliente.findByUsuario(username);
        if (cliente != null && cliente.getPassword().equals(password)) {
            return cliente;
        }
        return null;
    }

}