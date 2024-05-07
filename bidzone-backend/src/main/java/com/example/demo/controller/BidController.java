package com.example.demo.controller;

import com.example.demo.dto.BidDTO;
import com.example.demo.entity.BidEntity;
import com.example.demo.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    // Endpoint to create a new bid
    @PostMapping
    public ResponseEntity<Void> createBid(@RequestParam("bid_value") int value,
                                          @RequestParam("itemId") int id,
                                          @RequestParam("username") String username) {
        BidEntity bid = new BidEntity();
        bid.setBid_value(value);
        bid.setItemId(id);
        bid.setUsername(username);
        bidService.createBid(bid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Endpoint to delete a bid by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBid(@PathVariable int id) {
        bidService.deleteBid(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to get all bids for a specific item
    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<BidDTO>> getAllBids(@PathVariable int itemId) {
        List<BidDTO> bids = bidService.getAllBids(itemId);
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    // Endpoint to get all bids for a specific item and user
    @GetMapping("/item/{itemId}/user/{userName}")
    public ResponseEntity<List<BidDTO>> getMyBids(@PathVariable int itemId, @PathVariable String userName) {
        List<BidDTO> bids = bidService.getMyBids(itemId, userName);
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }
}
