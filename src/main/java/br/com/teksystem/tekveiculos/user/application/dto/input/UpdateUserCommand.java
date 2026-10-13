package br.com.teksystem.tekveiculos.user.application.dto.input;

import br.com.teksystem.tekveiculos.user.domain.Role;

public record UpdateUserCommand(
        String name,

        String email,

        String password,

        Role role,

        Boolean active
) {
}
