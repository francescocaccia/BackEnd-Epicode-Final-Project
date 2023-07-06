//package main.controller;
//
//import main.enums.TipoCucina;
//import main.payload.LabelValuePayload;
//import main.payload.TipoCucinaPayload;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Arrays;
//import java.util.List;
//
//@RestController
//@RequestMapping("/tipoCucina")
//public class TipoCucinaController {
//
//    @GetMapping("/all")
//    public ResponseEntity<TipoCucinaPayload> getAllTipoCucina() {
//        TipoCucinaPayload tipoCucinaPayload = new TipoCucinaPayload();
//        List<LabelValuePayload> labelValuePayloads = Arrays.stream(TipoCucina.values())
//                .map(tipoCucina -> new LabelValuePayload(tipoCucina.getLabel(), tipoCucina.name()))
//                .toList();
//        tipoCucinaPayload.setLabelValuePayloads(labelValuePayloads);
//        return new ResponseEntity<>(tipoCucinaPayload, HttpStatus.OK);
//    }
//}
