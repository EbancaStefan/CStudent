package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.LocationDao;
import com.atlasoftware.cstudent.domain.LocationType;
import com.atlasoftware.cstudent.dto.LocationDto;

import java.util.UUID;
import java.util.stream.Collectors;

public class LocationConverter {
    public static LocationDao convertDtoToModel(LocationDto locationDto) {
        return new LocationDao(UUID.fromString(locationDto.getId()), locationDto.getName(), locationDto.getAddress(),
                locationDto.getLatLong(), LocationType.valueOf(locationDto.getType()), locationDto.getTotalRoomNumberByFloor(),
                locationDto.getTotalNumberOfFloors(), locationDto.getStudents().stream().map(StudentConverter::convertDtoToModel).collect(Collectors.toList()));
    }

    public static LocationDto convertModelToDto(LocationDao locationDao) {
        return new LocationDto(locationDao.getId().toString(), locationDao.getName(), locationDao.getAddress(),
                locationDao.getLatLong(), locationDao.getType().toString(), locationDao.getTotalRoomNumberByFloor(),
                locationDao.getTotalNumberOfFloors(), locationDao.getStudents().stream().map(StudentConverter::convertModelToDto).collect(Collectors.toList()));
    }
}
