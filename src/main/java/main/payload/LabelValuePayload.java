package main.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LabelValuePayload {
    private String label;
    private String value;

}
