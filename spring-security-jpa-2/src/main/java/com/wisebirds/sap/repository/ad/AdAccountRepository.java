package com.wisebirds.sap.repository.ad;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisebirds.sap.domain.ad.AdAccount;

public interface AdAccountRepository extends JpaRepository<AdAccount, Long> {

}
