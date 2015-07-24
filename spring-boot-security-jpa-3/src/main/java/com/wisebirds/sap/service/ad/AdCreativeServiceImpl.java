package com.wisebirds.sap.service.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.wisebirds.sap.Application;
import com.wisebirds.sap.domain.SimplePagingImpl;
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
	public ModelAndView getList(SimplePagingImpl spi) {
		// List<AdCreative> creativeList = null;
		Page<AdCreative> page = null;
		switch (spi.getType()) {
			case "all":
				page = adCreativeRepository.findAll(new PageRequest(spi.getPage(), spi.getLimit()));
				break;
			case "pending":
				page = adCreativeRepository.findAllByRunStatusOrderByCreateTimeAsc(AdCreative.RUN_STATUS_PENDING, new PageRequest(spi.getPage(), spi.getLimit())); // , new PageRequest(1, 10)
				break;
			case "approved":
				page = adCreativeRepository.findAllByRunStatusOrderByCreateTimeAsc(AdCreative.RUN_STATUS_ACTIVE, new PageRequest(spi.getPage(), spi.getLimit(), Direction.ASC, "id"));
				break;
			case "rejected":
				page = adCreativeRepository.findAllByRunStatusOrderByCreateTimeAsc(AdCreative.RUN_STATUS_DISAPPROVED, new PageRequest(spi.getPage(), spi.getLimit(), Direction.ASC, "id"));
				break;
			default:
				throw new IllegalArgumentException("처리할 수 없는 타입입니다.");
		}
		// creativeList.get(0).setAdData(new AdData(1L, 1));
		// creativeList.get(1).setAdData(new AdData(1L, 1));
		// creativeList.get(2).setAdData(new AdData(1L, 1));
		// creativeList.get(3).setAdData(new AdData(1L, 2));
		// creativeList.get(4).setAdData(new AdData(1L, 10));
		// creativeList.get(5).setAdData(new AdData(1L, 11));
		// creativeList.get(6).setAdData(new AdData(1L, 2));
		jedisRepository.putObject("test", page.getContent());
		return new ModelAndView("ad/creative/verification", "page", page);
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
