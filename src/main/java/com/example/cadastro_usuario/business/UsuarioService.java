package com.example.cadastro_usuario.business;


import com.example.cadastro_usuario.infrastructure.entity.Usuario;
import com.example.cadastro_usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void salveUsuario(Usuario usuario){
        usuarioRepository.saveAndFlush(usuario);
    }

    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }

    public void deletarPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
    public void atualizarUsuarioPorId(Integer id, Usuario usuario){
        Usuario usuarioEntity = usuarioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuario não encontrado"));
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() :
                        usuarioEntity.getEmail())
                .name(usuario.getName() != null ? usuario.getName() :
                        usuarioEntity.getName())
                .id(usuarioEntity.getId())
                .build();

        usuarioRepository.saveAndFlush(usuarioAtualizado);
    }

}
