package com.wisebirds.sap.service.ad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.wisebirds.sap.Application;
import com.wisebirds.sap.domain.ad.AdCreative;
import com.wisebirds.sap.domain.ad.AdVerifyHistory;
import com.wisebirds.sap.repository.JedisRepository;
import com.wisebirds.sap.repository.ad.AdCreativeRepository;
import com.wisebirds.sap.repository.ad.AdVerifyHistoryRepository;
import com.wisebirds.sap.service.ad.form.VerificationResultForm;

@Service
public class AdCreativeServiceImpl implements AdCreativeService {
	@Autowired
	private JedisRepository jedisRepository;
	@Autowired
	private AdCreativeRepository adCreativeRepository;
	@Autowired
	private AdVerifyHistoryRepository adVerifyHistoryRepository;

	@Override
	public ModelAndView getList(int i) {
		List<AdCreative> creativeList = adCreativeRepository.findAllByRunStatus(0);
		System.out.println(jedisRepository.get("jongmin"));
		return new ModelAndView("ad/creative/verification", "creativeList", creativeList);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ModelAndView verify(VerificationResultForm vrf) {
		AdCreative creative = adCreativeRepository.findOne(vrf.getCreativeId());
		int status = 0;
		switch (vrf.getAction()) {
			case VerificationResultForm.ACTION_APPROVE:
				status = AdCreative.RUN_STATUS_ACTIVE;
				break;
			case VerificationResultForm.ACTION_REJECT:
				status = AdCreative.RUN_STATUS_DISAPPROVED;
				break;
		}
		creative.setRunStatus(status);
		adCreativeRepository.save(creative);
		adVerifyHistoryRepository.save(new AdVerifyHistory(vrf.getCreativeId(), status, vrf.getReason()));
		return new ModelAndView("common/server_response", "data", Application.GSON.toJson(true));
	}
}
