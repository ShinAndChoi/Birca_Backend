package com.app.birca.service;

import com.app.birca.domain.Category;
import com.app.birca.domain.RoleType;
import com.app.birca.domain.entity.Cafe;
import com.app.birca.domain.entity.Idol;
import com.app.birca.domain.entity.IdolGroup;
import com.app.birca.domain.entity.User;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.request.SaveCafeInformRequest;
import com.app.birca.dto.response.IdolGroupResponse;
import com.app.birca.dto.response.IdolResponse;
import com.app.birca.dto.response.businesslicense.BusinessLicenseResponse;
import com.app.birca.exception.UserNotFound;
import com.app.birca.repository.CafeRepository;
import com.app.birca.repository.IdolGroupRepository;
import com.app.birca.repository.IdolRepository;
import com.app.birca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OnBoardingService {

    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;
    private final IdolRepository idolRepository;
    private final IdolGroupRepository idolGroupRepository;
    private final BusinessLicenseService businessLicenseService;
    private final OcrService ocrService;

    public List<IdolGroupResponse> findAllIdolGroups() {
        List<IdolGroup> groups = idolGroupRepository.findAll();
        return groups.stream().map(g -> IdolGroupResponse.builder()
                .koreanName(g.getKoreanName())
                .englishName(g.getEnglishName())
                .imageUrl(g.getImageUrl())
                .build())
                .collect(toList());
    }

    public List<IdolGroupResponse> findByCategory(String type) {
        Category category = Category.fromString(type);
        List<IdolGroup> groups = idolGroupRepository.findByCategory(category);
        return groups.stream().map(g -> IdolGroupResponse.builder()
                        .koreanName(g.getKoreanName())
                        .englishName(g.getEnglishName())
                        .imageUrl(g.getImageUrl())
                        .build())
                .collect(toList());
    }

    public IdolGroupResponse findBySearching(String groupName) {
        IdolGroup group = idolGroupRepository.findByKoreanName(groupName)
                .orElseThrow();

        return IdolGroupResponse.builder()
                .koreanName(group.getKoreanName())
                .englishName(group.getEnglishName())
                .imageUrl(group.getImageUrl())
                .build();
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

    @Transactional
    public void saveCafeInform(LoginUser loginUser, SaveCafeInformRequest request) throws IOException {
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(UserNotFound::new);

//        RoleType roleType = user.getRoleType();
//        if (!roleType.getType().equals("cafeOwner")) {
//            throw new IllegalArgumentException("카페 사장만 등록할 수 있습니다.");
//        }

        String ownerName = request.getOwnerName();
        String cafeName = request.getCafeName();
        String contactNumber = request.getContactNumber();
        MultipartFile businessLicense = request.getBusinessLicense();

//        businessLicenseService.uploadBusinessLicense(businessLicense);
//        BusinessLicenseResponse businessLicenseInfo = ocrService.getBusinessLicenseInfo(businessLicense, "사업자 등록증");
//        businessLicenseService.saveRegistrationNumber(loginUser, businessLicenseInfo);

        Cafe cafe = Cafe.builder()
                .ownerName(ownerName)
                .cafeName(cafeName)
                .contact(contactNumber)
                .user(user)
                .build();

        cafeRepository.save(cafe);
    }
}
