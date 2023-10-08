package com.app.birca.service;

import com.app.birca.domain.entity.Idol;
import com.app.birca.domain.entity.IdolGroup;
import com.app.birca.dto.response.IdolGroupResponse;
import com.app.birca.dto.response.IdolResponse;
import com.app.birca.repository.IdolGroupRepository;
import com.app.birca.repository.IdolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OnBoardingService {

    private final IdolRepository idolRepository;
    private final IdolGroupRepository idolGroupRepository;

    public List<IdolGroupResponse> getIdolGroups() {
        List<IdolGroup> groups = idolGroupRepository.findAll();
        return groups.stream().map(g -> IdolGroupResponse.builder()
                .koreanName(g.getKoreanName())
                .englishName(g.getEnglishName())
                .imageUrl(g.getImageUrl())
                .build())
                .collect(toList());
    }

    public List<IdolResponse> getIdols(String idolGroup) {
        IdolGroup group = idolGroupRepository.findByKoreanName(idolGroup)
                .orElseThrow();

        List<Idol> idols = idolRepository.findByIdolGroupId(group.getId());
        return idols.stream().map(i -> IdolResponse.builder()
                .koreanName(i.getKoreanName())
                .englishName(i.getEnglishName())
                .imageUrl(i.getImageUrl())
                .build())
                .collect(toList());
    }
}
