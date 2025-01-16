package com.desafio.forohub.domain.respuesta.validations.create;

import com.desafio.forohub.domain.respuesta.dto.CrearRespuestaDTO;
import com.desafio.forohub.domain.topico.Estado;
import com.desafio.forohub.domain.topico.repository.TopicoRepository;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RespuestaTopicoValida implements ValidarRespuestaCreada{

    @Autowired
    private TopicoRepository repository;

    @Override
    public void validate(CrearRespuestaDTO data) {
        var topicoExiste = repository.existsById(data.topicoId());

        if (!topicoExiste) {
            throw new ValidationException("El topico no existe.");
        }

        var topicoAbierto = repository.findById(data.topicoId()).get().getEstado();

        if (topicoAbierto != Estado.OPEN) {
            throw new ValidationException("El topico debe estar abierto para responder.");
        }
    }
}
