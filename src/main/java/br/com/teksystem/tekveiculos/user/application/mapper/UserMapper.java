package br.com.teksystem.tekveiculos.user.application.mapper;

import br.com.teksystem.tekveiculos.user.application.dto.input.CreateUserCommand;
import br.com.teksystem.tekveiculos.user.application.dto.output.UserResponse;
import br.com.teksystem.tekveiculos.user.domain.Email;
import br.com.teksystem.tekveiculos.user.domain.Name;
import br.com.teksystem.tekveiculos.user.domain.Password;
import br.com.teksystem.tekveiculos.user.domain.User;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "active", constant = "true"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "role", source = "role"),
    })
    User toDomain(CreateUserCommand createUserCommand);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "password", source = "password"),
    })
    CreateUserCommand toCommand(User user);

    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "name", source = "user.name.name"),
            @Mapping(target = "email", source = "user.email.email"),
            @Mapping(target = "role", source = "user.role"),
            @Mapping(target = "active", source = "user.true"),
    })
    UserResponse toResponse(User user);

    // Conversions
    default String mapName(Name name) {
        return name == null ? null : name.name();
    }

    default Name toName(String name) {
        return name == null ? null : new Name(name);
    }

    default String mapEmail(Email email) {
        return email == null ? null : email.email();
    }

    default Email toEmail(String email) {
        return email == null ? null : new Email(email);
    }

    default String mapPassword(Password password) {
        return password == null ? null : password.password();
    }

    default Password toPassword(String password) {
        return password == null ? null : new Password(password);
    }
}
