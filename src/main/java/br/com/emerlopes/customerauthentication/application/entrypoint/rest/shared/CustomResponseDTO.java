package br.com.emerlopes.customerauthentication.application.entrypoint.rest.shared;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomResponseDTO<T> {
    private T data;
}