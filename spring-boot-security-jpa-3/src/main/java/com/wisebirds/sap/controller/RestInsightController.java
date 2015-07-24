package com.wisebirds.sap.controller;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wisebirds.sap.Application;
import com.wisebirds.sap.domain.account.Account;
import com.wisebirds.sap.domain.ad.form.InsightForm;
import com.wisebirds.sap.service.ad.AdAccountService;
import com.wisebirds.sap.service.ad.AdCampaignSetService;

@RestController
@SessionAttributes("me")
public class RestInsightController extends AbstractRestV1_0Controller {
	@Autowired
	AdAccountService adAccountService;
	@Autowired
	AdCampaignSetService adCampaignGroupService;

	@RequestMapping("/{objectId}/insight")
	public Object getHomePage(@PathVariable String objectId, @ModelAttribute("me") Account me, @ModelAttribute("form") InsightForm insightFormData) throws URISyntaxException {
		System.out.println(Application.GSON.toJson(objectId));
		System.out.println(Application.GSON.toJson(me));
		System.out.println(Application.GSON.toJson(insightFormData));

		switch (Application.getObjectType(objectId)) {
			case Application.OBJECT_AD_ACCOUNT:
				insightFormData.setId(Long.valueOf(objectId));
				return adAccountService.insight(me, insightFormData);
			default:
				throw new IllegalArgumentException("처리할 수 없는 인사이트입니다.");
		}
	}

	@RequestMapping("/insight/test")
	public Object test(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Account account = new Account();
		account.setId(1L);
		account.setEmail("tester");
		account.setName("하잉");
		session.setAttribute("me", account);
		return Application.GSON.toJson(true);
	}
	// try {
	// Path wiki_path = Paths.get(RestInsightController.class.getClassLoader().getResource("dummy/ad_insights.json").toURI());
	// Charset charset = Charset.forName("UTF-8");
	// StringBuilder sb = new StringBuilder();
	// Files.readAllLines(wiki_path, charset).forEach(sb::append);
	// AdInsights adInsights = Application.GSON.fromJson(sb.toString(), AdInsights.class);
	// return new ModelAndView("insight/data_table", "adInsights", adInsights.getData());
	// } catch (IOException e) {
	// throw new IllegalArgumentException(e);
	// }
}
