package com.desafio.forohub.domain.topico.validations.create;

import com.desafio.forohub.domain.curso.repository.CursoRepository;
import com.desafio.forohub.domain.topico.dto.CrearTopicoDTO;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarCursoCreado implements ValidarTopicoCreado {

    @Autowired
    private CursoRepository repository;

    @Override
    public void validate(CrearTopicoDTO data) {
        var ExisteCurso = repository.existsById(data.cursoId());
        if (!ExisteCurso) {
            throw new ValidationException("Curso no encontrado");
        }

        var cursoHabilitado = repository.findById(data.cursoId()).get().getActivo();
        if (!cursoHabilitado){
            throw new ValidationException("El curso no está habilitado en este momento");
        }
    }
}
