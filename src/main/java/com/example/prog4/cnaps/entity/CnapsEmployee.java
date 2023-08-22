package com.example.prog4.cnaps.entity;

import com.example.prog4.model.Phone;
import com.example.prog4.repository.entity.Position;
import com.example.prog4.repository.entity.enums.Csp;
import com.example.prog4.repository.entity.enums.Sex;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
public class CnapsEmployee implements Serializable {
    private String id;
    private String firstName;
    private String lastName;

    private MultipartFile image;
    private String stringImage;

    private Csp csp;
    private Sex sex;
    private String cin;
    @Column(unique = true)
    private String cnaps;
    private String number;
    private String address;
    private Integer childrenNumber;
    private String personalEmail;
    private String professionalEmail;
    private String registrationNumber;
    private LocalDate birthDate;
    private LocalDate entranceDate;
    private LocalDate departureDate;
    private List<Position> positions;
    private List<Phone> phones;
    private String endToEndId;
}
