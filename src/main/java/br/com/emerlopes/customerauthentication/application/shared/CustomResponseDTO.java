package br.com.emerlopes.customerauthentication.application.shared;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomResponseDTO<T> {
    private T data;
}