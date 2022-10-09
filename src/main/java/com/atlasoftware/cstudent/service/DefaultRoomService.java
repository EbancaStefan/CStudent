package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("defaultRoomService")
public class DefaultRoomService implements RoomService {
    @Autowired
    RoomService roomService;
    @Autowired
    LocationService locationService;


    @Override
    public RoomDao findByLocationNameAndName(String locationName, String roomName) {
        return roomService.findByLocationNameAndName(locationName,roomName);
    }
}
