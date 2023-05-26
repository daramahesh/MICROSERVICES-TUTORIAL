package org.hotel.service.services;

import org.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);
    List<Hotel> getAll();
    Hotel getHotel(String hotelId);
}
