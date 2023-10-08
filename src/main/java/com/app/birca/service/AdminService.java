package com.app.birca.service;

import com.app.birca.domain.entity.Idol;
import com.app.birca.domain.entity.IdolGroup;
import com.app.birca.dto.request.IdolNameRequest;
import com.app.birca.dto.request.InsertIdolGroupRequest;
import com.app.birca.dto.request.InsertIdolRequest;
import com.app.birca.repository.IdolGroupRepository;
import com.app.birca.repository.IdolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final IdolRepository idolRepository;
    private final IdolGroupRepository idolGroupRepository;
    private final S3Service s3Service;

    public void saveIdolGroup(InsertIdolGroupRequest request) throws IOException {
        MultipartFile file = request.getMultipartFile();
        String imageUrl = s3Service.uploadImage(file);

        String koreanName = request.getKoreanName();
        String englishName = request.getEnglishName();
        IdolGroup group = new IdolGroup(koreanName, englishName, imageUrl);
        idolGroupRepository.save(group);
    }

    public void saveIdol(InsertIdolRequest request) throws IOException {
        String groupName = request.getKoreanName();
        IdolGroup idolGroup = idolGroupRepository.findByKoreanName(groupName)
                .orElseThrow();

        List<IdolNameRequest> idols = request.getIdols();
        List<MultipartFile> files = request.getFiles();

        for (int i = 0; i < idols.size(); i++) {
            saveIdolWithImage(idols.get(i), files.get(i), idolGroup);
        }
    }

    private void saveIdolWithImage(IdolNameRequest idolRequest, MultipartFile file, IdolGroup idolGroup) throws IOException {
        String imageUrl = s3Service.uploadImage(file);
        String koreanName = idolRequest.getKoreanName();
        String englishName = idolRequest.getEnglishName();
        Idol idol = new Idol(koreanName, englishName, imageUrl, idolGroup);
        idolRepository.save(idol);
    }


}
