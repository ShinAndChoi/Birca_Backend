package com.app.birca.dto.response.businesslicense;

import lombok.Getter;

import java.util.List;

@Getter
public class BizLicense {

    private Meta meta;
    private Result result;

    @Getter
    public static class Meta {
        private String estimatedLanguage;
    }

    @Getter
    public static class Result {
        private List<BusinessInfo> birth;
        private List<BusinessInfo> bisAddress;
        private List<BusinessInfo> bisItem;
        private List<BusinessInfo> registerNumber;
        private List<BusinessInfo> bisType;
        private List<BusinessInfo> companyName;
    }

    @Getter
    public static class BusinessInfo {
        private String text;
        private List<BoundingPoly> boundingPolys;
    }

    @Getter
    public static class BoundingPoly {
        private List<Vertex> vertices;
    }

    @Getter
    public static class Vertex {
        private double x;
        private double y;
    }

}
