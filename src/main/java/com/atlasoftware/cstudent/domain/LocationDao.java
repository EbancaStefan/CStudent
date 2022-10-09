package com.atlasoftware.cstudent.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationDao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String name;
    private String address;
    @Column(name = "lat_long")
    private String latLong;
    @Enumerated(EnumType.STRING)
    private LocationType type;
    @Column(name = "total_room_number_by_floor")
    private int totalRoomNumberByFloor;
    @Column(name = "total_number_of_floors")
    private int totalNumberOfFloors;
    @OneToMany(mappedBy = "locationDao", cascade = CascadeType.ALL)
    private List<StudentDao> students;
}
