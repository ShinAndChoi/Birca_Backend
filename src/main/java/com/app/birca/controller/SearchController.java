package com.app.birca.controller;

import com.app.birca.dto.request.CafeSearchRequest;
import com.app.birca.dto.response.CafeSearchResponse;
import com.app.birca.service.SearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/cafes")
    public List<CafeSearchResponse> searchCafe(@RequestParam int page, @ModelAttribute @Valid CafeSearchRequest request) {
        log.info("idol = {}", request.getIdolName());
        log.info("startDate = {} ", request.getStartDate());
        log.info("endDate = {} ", request.getEndDate());
        return searchService.getReservedCafe(page, request);
    }


}
