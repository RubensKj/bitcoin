package com.rubenskj.service;

import com.rubenskj.exception.BadRequestException;
import com.rubenskj.model.Ordem;
import com.rubenskj.model.Usuario;
import com.rubenskj.repository.OrdemRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;
import java.time.LocalDate;

@ApplicationScoped
public class OrdemService {

    @Inject
    OrdemRepository ordemRepository;

    public void persist(SecurityContext securityContext, Ordem ordem) {
        Usuario usuario = (Usuario) Usuario.findByIdOptional(ordem.getUserId()).orElseThrow(() -> new BadRequestException("Error during find by id. Id -> " + ordem.getUserId()));

        if (!usuario.getUsername().equals(securityContext.getUserPrincipal().getName())) {
            throw new BadRequestException("The user is not the same.");
        }

        ordem.setData(LocalDate.now());
        ordem.setStatus("ENVIADA");

        ordemRepository.persist(ordem);
    }
}
