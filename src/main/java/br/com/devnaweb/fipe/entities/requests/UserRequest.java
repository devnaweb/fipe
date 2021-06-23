package br.com.devnaweb.fipe.entities.requests;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserRequest {

    @NotNull
    private String document;

    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String birthDate;
}
