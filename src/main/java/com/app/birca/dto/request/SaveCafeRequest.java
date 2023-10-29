package com.app.birca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class SaveCafeRequest {

    private String cafeName;
    private String ownerName;
    private String introduction;
    private String contact;
    private int capacity;
    private int price;
    private Double xAxis;
    private Double yAxis;
    private String area;
    private List<LocalDate> localDates;
    private MultipartFile businessLicense;
    private List<MultipartFile> cafeImages;

    public Double getxAxis() {
        return xAxis;
    }

    public Double getyAxis() {
        return yAxis;
    }

}
