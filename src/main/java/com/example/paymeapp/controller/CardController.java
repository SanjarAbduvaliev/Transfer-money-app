package com.example.paymeapp.controller;

import com.example.paymeapp.payload.CardDTO;
import com.example.paymeapp.payload.OutputDto;
import com.example.paymeapp.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/card")
public class CardController {
    private final CardService cardService;
    @Autowired
    public CardController(CardService cardService){
        this.cardService=cardService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> addCard(@Valid @RequestBody CardDTO cardDTO) {
        return cardService.addCard(cardDTO);
    }

    @GetMapping("/getCard/{id}")
    public ResponseEntity<?> getCard(@PathVariable Integer id) {
        return cardService.getCardID(id);

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCard(@Valid @RequestBody CardDTO cardDTO,@PathVariable Integer id){
        return cardService.editCard(cardDTO,id);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Integer id){
        return cardService.deleteCard(id);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> trensfer(@Valid@RequestBody OutputDto outputDto){
        return cardService.transferOutput(outputDto);
    }
}