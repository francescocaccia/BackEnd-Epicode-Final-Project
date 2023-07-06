package main.controller;

import main.service.LuogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/luogo")
public class LuogoController {

    @Autowired
    LuogoService luogoService;

    @GetMapping("/citta")
    public ResponseEntity<List<String>> findCitta(){
        List<String> citta = luogoService.findCitta();
        return new ResponseEntity<>(citta, HttpStatus.OK) ;
    }
}
