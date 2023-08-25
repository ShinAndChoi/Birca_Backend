package com.app.birca.repository;

import com.app.birca.domain.entity.BusinessLicense;
import com.app.birca.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusinessLicenseRepository extends JpaRepository<BusinessLicense, Long> {
    Boolean existsByRegistrationNumber(String registrationNumber);
}
