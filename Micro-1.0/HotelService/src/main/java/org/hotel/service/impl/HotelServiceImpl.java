package org.hotel.service.impl;

import org.hotel.service.entities.Hotel;
import org.hotel.service.exception.ResourceNotFoundException;
import org.hotel.service.repositories.HotelRepository;
import org.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public Hotel createHotel(Hotel hotel) {

        String randomId = UUID.randomUUID().toString();
        hotel.setHotelId(randomId);
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return this.hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("hotel not with given id"));
    }
}
