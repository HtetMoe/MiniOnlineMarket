package com.momarket.users.buyer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Register a new buyer
    public BuyerDTO registerBuyer(BuyerDTO buyerDTO) {
        // Convert DTO to entity
        Buyer buyer = modelMapper.map(buyerDTO, Buyer.class);
        // Save to database
        Buyer savedBuyer = buyerRepository.save(buyer);
        // Convert the saved entity back to DTO
        return modelMapper.map(savedBuyer, BuyerDTO.class);
    }

    // Get buyer details by ID
    public BuyerDTO getBuyerById(Long buyerId) {
        Optional<Buyer> buyer = buyerRepository.findById(buyerId);
        if (buyer.isPresent()) {
            return modelMapper.map(buyer.get(), BuyerDTO.class);
        }
        throw new RuntimeException("Buyer not found");
    }

    // Update buyer details
    public BuyerDTO updateBuyer(Long buyerId, BuyerDTO buyerDTO) {
        Optional<Buyer> buyerOptional = buyerRepository.findById(buyerId);
        if (buyerOptional.isPresent()) {
            Buyer buyer = buyerOptional.get();
            // Update the fields
            buyer.setName(buyerDTO.getName());
            buyer.setEmail(buyerDTO.getEmail());
            buyer.setAddress(buyerDTO.getAddress());
            // Save updated buyer
            Buyer updatedBuyer = buyerRepository.save(buyer);
            return modelMapper.map(updatedBuyer, BuyerDTO.class);
        }
        throw new RuntimeException("Buyer not found");
    }

    // Delete a buyer
    public void deleteBuyer(Long buyerId) {
        Optional<Buyer> buyer = buyerRepository.findById(buyerId);
        if (buyer.isPresent()) {
            buyerRepository.delete(buyer.get());
        } else {
            throw new RuntimeException("Buyer not found");
        }
    }
}

