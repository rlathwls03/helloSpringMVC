package kr.ac.hansung.cse.controller.service;

import kr.ac.hansung.cse.controller.dao.OfferDao;
import kr.ac.hansung.cse.controller.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    //service -> dao
    @Autowired
    private OfferDao offerDao;

    public List<Offer> getAllOffers() {
        return offerDao.getOffers();
    }

    public void insertOffer(Offer offer) {
        offerDao.insert(offer);
    }
}
