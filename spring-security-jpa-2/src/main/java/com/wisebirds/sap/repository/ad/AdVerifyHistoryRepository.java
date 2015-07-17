package com.wisebirds.sap.repository.ad;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisebirds.sap.domain.ad.AdVerifyHistory;

public interface AdVerifyHistoryRepository extends JpaRepository<AdVerifyHistory, Long> {
}
