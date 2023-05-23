package org.hotel.service.controllers;

import org.hotel.service.entities.Hotel;
import org.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/create")
    public ResponseEntity<Hotel> createUser(@RequestBody Hotel hotel) {
        Hotel hotel1 = this.hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    //getAll
    @GetMapping
    public ResponseEntity<List<Hotel>> getAll() {
        List<Hotel> user = this.hotelService.getAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    //getUser
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getUser(@PathVariable("hotelId") String hotelId) {
        Hotel user = this.hotelService.getHotel(hotelId);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }
}
