package kr.ac.hansung.cse.controller.controller;

import jakarta.validation.Valid;
import kr.ac.hansung.cse.controller.model.Offer;
import kr.ac.hansung.cse.controller.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OfferController {
    //Controller -> Service -> Dao
    @Autowired
    private OfferService offerService;

    @GetMapping("/offers")
    public String showOffers(Model model) {
        List<Offer> offers = offerService.getAllOffers();
        model.addAttribute("id_offers", offers);

        return "offers";
    }

    @GetMapping("/createoffer")
    public String createOffer(Model model) {
        model.addAttribute("offer", new Offer()); // 빈 객체 입력
        return "createoffer";
    }

    @PostMapping("docreate")
    public String doCreate(Model model, @Valid Offer offer, BindingResult result) {
        //System.out.println(offer);

        if(result.hasErrors()) {
            System.out.println("== Form data does not validate ==");

            List<ObjectError> errors = result.getAllErrors();

            for(ObjectError error:errors) {
                System.out.println(error.getDefaultMessage());
            }
            return "createoffer"; // 사용자 입력 받은 객체 입력
        }

        // Controller -> Service -> Dao
        // 에러가 없을 경우, 데이터베이스에 저장
        offerService.insertOffer(offer);
        return "offercreated";
    }
}
