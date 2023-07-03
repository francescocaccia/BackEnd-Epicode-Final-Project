package main.payload;


import lombok.Data;

import java.util.List;

@Data
public class TipoCucinaPayload {

    private List<LabelValuePayload> labelValuePayloads;
}
