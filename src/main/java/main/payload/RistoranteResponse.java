package main.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RistoranteResponse {
    private Long id;
    private String nomeRistorante;
}
