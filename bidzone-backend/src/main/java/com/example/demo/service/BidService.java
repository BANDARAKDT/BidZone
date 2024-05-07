package com.example.demo.service;

import com.example.demo.dto.BidDTO;
import com.example.demo.entity.BidEntity;
import com.example.demo.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    public void createBid(BidEntity bid) {
        bidRepository.save(bid);
    }

    public void deleteBid(int id) {
        bidRepository.deleteById(id);
    }

    public List<BidDTO> getAllBids(int itemId) {
        List<BidEntity> bidEntities = bidRepository.findByItemID(itemId);
        int length = bidEntities.size();
        List<BidDTO> bids = new ArrayList<>(length);
        for (BidEntity bid : bidEntities) {
           BidDTO bidDTO = new BidDTO();
           bidDTO.setItemId(bid.getItemId());
           bidDTO.setPrice(bid.getBid_value());
           bidDTO.setUserName(bid.getUsername());
           bidDTO.setBidId(bid.getId());
           bids.add(bidDTO);
        }
        return bids;
    }

    public List<BidDTO> getMyBids(int itemId, String userName) {
        List<BidEntity> bidEntities = bidRepository.findByItemIdAndUserName(itemId, userName);
        int length = bidEntities.size();
        List<BidDTO> bids = new ArrayList<>(length);
        for (BidEntity bid : bidEntities) {
            BidDTO bidDTO = new BidDTO();
            bidDTO.setItemId(bid.getItemId());
            bidDTO.setPrice(bid.getBid_value());
            bidDTO.setUserName(bid.getUsername());
            bidDTO.setBidId(bid.getId());
            bids.add(bidDTO);
        }
        return bids;
    }
}
