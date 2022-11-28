package com.example.paymeapp.service;

import com.example.paymeapp.entity.Card;
import com.example.paymeapp.entity.Income;
import com.example.paymeapp.entity.Output;
import com.example.paymeapp.entity.User;
import com.example.paymeapp.payload.CardDTO;
import com.example.paymeapp.payload.OutputDto;
import com.example.paymeapp.repository.CardRepository;
import com.example.paymeapp.repository.IncomeRepository;
import com.example.paymeapp.repository.OutpuRepository;
import com.example.paymeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CardService {

    @Autowired
    User user;
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    OutpuRepository outpuRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;

    public ResponseEntity<?> addCard(CardDTO cardDTO) {
        Card card = new Card();
        if (cardRepository.existsByCardNumberNot(cardDTO.getCardNumber())) {
            card.setCardNumber(cardDTO.getCardNumber());
            card.setName(cardDTO.getName());
            card.setExpireDate(cardDTO.getExpireDate());
            cardRepository.save(card);
            return ResponseEntity.ok("Karta saqlandi");
        }

        return ResponseEntity.status(403).body("Bunday karta mavjud");
    }

    public ResponseEntity<?> getCardID(Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isEmpty())
            return ResponseEntity.status(404).body("BUnday karta mavjud emas");
        return ResponseEntity.ok(optionalCard.get());
    }

    public ResponseEntity<?> editCard(CardDTO cardDTO, Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isEmpty()) {
            return ResponseEntity.status(404).body("Karta topilmadi");
        }
        Card card = optionalCard.get();
        card.setName(cardDTO.getName());
        cardRepository.save(card);
        return ResponseEntity.ok("Karta tahrirlandi");
    }

    public ResponseEntity<?> deleteCard(Integer id) {
        cardRepository.deleteById(id);
        return ResponseEntity.ok("Karta  o'chirildi");
    }

    public ResponseEntity<?> transferOutput(OutputDto outputDto) {
        Card byHimCard = cardRepository.findByCardNumber(outputDto.getFromCardNumber());

        User myAccount = userRepository.findByPhoneNumber(user.getPhoneNumber());
        List<Card> myCards = myAccount.getCards();

        if (byHimCard != null) {


            Card myCard = myCards.get(outputDto.getMyCardIndex());

            if (myCard.getBalance()< outputDto.getSumma())
                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body("Hisobingizdagi mablag yetarlik emas");

            double comission=outputDto.getSumma() * 0.003;

            myCard.setBalance(-outputDto.getSumma() - comission);

            byHimCard.setBalance(+outputDto.getSumma());

            List<Output> outputsMy = myCard.getOutputs();

            Random randNumIncomeIndex = new Random();

            int tranferIndex = randNumIncomeIndex.nextInt();

            outputsMy.add(tranferIndex, new Output(outputDto.getSumma() + outputDto.getSumma() * 0.003,
                    new Date(),
                    outputDto.getComment(),
                    byHimCard.getCardNumber(),
                    comission));

            List<Income> income = myCard.getIncome();
            income.add(randNumIncomeIndex.nextInt(), new Income(
                    null, new Date(), outputDto.getComment()
            ));
            incomeRepository.save(income.get(randNumIncomeIndex.nextInt()));
            outpuRepository.save(outputsMy.get(tranferIndex));
            cardRepository.save(byHimCard);
            cardRepository.save(myCard);

            return ResponseEntity.ok("Transfer muvofaqqiyatlik amalga oshirildi\nO'tkazma sanasi:" +
                            new Date());


        }

        return ResponseEntity.status(404).body("Karta  topilmadi tekshirib qayta  kiriting");
    }

}
