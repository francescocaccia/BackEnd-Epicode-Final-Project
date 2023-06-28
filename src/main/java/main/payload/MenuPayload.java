package main.payload;

import lombok.Data;
import main.entities.Piatto;

import java.util.List;

@Data
public class MenuPayload {

    private List<PiattoPayload> piatti;
}
