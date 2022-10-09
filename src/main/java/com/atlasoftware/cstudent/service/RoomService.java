package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.RoomDao;

public interface RoomService {
    RoomDao findByLocationNameAndName(String locationName,String roomName);
}
