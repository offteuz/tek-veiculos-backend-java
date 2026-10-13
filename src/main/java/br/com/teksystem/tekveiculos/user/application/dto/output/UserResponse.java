package br.com.teksystem.tekveiculos.user.application.dto.output;

import br.com.teksystem.tekveiculos.user.domain.Role;

public record UserResponse(
        Long id,

        String name,

        String email,

        Role role,

        Boolean active
) {
}
