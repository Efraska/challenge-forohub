package com.desafio.forohub.domain.respuesta.validations.create;

import com.desafio.forohub.domain.respuesta.dto.CrearRespuestaDTO;
import com.desafio.forohub.domain.usuario.repository.UsuarioRepository;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RespuestaUsuarioValida implements ValidarRespuestaCreada{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validate(CrearRespuestaDTO data) {
        var usuarioExiste = repository.existsById(data.usuarioId());

        if (!usuarioExiste) {
            throw new RuntimeException("El usuario no existe.");
        }

        var usuarioHabilitado = repository.findById(data.usuarioId()).get().isEnabled();

        if (!usuarioHabilitado) {
            throw new ValidationException("El usuario está deshabilitado.");
        }
    }
}
