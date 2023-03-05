package com.soft.BackendSpringBootJuris.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.BackendSpringBootJuris.models.Usuarios;
import com.soft.BackendSpringBootJuris.services.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuarios> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuarios buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuarios criarUsuario(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuarios atualizarUsuario(Long id, Usuarios usuarioAtualizado) {
        Usuarios usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente == null) {
            return null;
        }
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setSobrenome(usuarioAtualizado.getSobrenome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        return usuarioRepository.save(usuarioExistente);
    }

    public boolean deletarUsuario(Long id) {
        Usuarios usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente == null) {
            return false;
        }
        usuarioRepository.delete(usuarioExistente);
        return true;
    }
}
