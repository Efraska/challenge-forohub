package com.desafio.forohub.domain.topico.validations.create;

import com.desafio.forohub.domain.usuario.repository.UsuarioRepository;
import com.desafio.forohub.domain.topico.dto.CrearTopicoDTO;
import jakarta.xml.bind.ValidationException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarTopicoUsuario implements ValidarTopicoCreado{

    @Autowired
    private UsuarioRepository repository;

    @SneakyThrows
    @Override
    public void validate(CrearTopicoDTO data) {
        var existeUsuario = repository.existsById(data.usuarioId());
        if (!existeUsuario) {
            throw new ValidationException("Este usuario no existe");
        }

        var usuarioHabilitado = repository.findById(data.usuarioId()).get().getEnabled();
        if (!usuarioHabilitado){
            throw new ValidationException("Este usuario fue deshabilitado");
        }
    }
}
