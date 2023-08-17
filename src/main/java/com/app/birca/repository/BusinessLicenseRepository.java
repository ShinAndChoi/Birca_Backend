package com.app.birca.repository;

import com.app.birca.domain.entity.BusinessLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessLicenseRepository extends JpaRepository<BusinessLicense, Long> {
    Boolean existsByRegistrationNumber(String registrationNumber);
}
