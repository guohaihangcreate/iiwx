package ren.xiangmu.iiwx.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ren.xiangmu.iiwx.entity.SalaryDetail;
import ren.xiangmu.iiwx.entity.Wx_user;
import ren.xiangmu.iiwx.service.WxUserService;
import ren.xiangmu.iiwx.util.WxAuthUtil;

@Controller
public class RemoteDateController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RemoteDateController.class);

	@Autowired
	private Environment env;

	@Autowired
	private WxUserService wxUserService;

	public RemoteDateController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/salaryRemoteDate", method = RequestMethod.GET)
	private String salaryRemoteDate(@RequestParam("queryDate") String queryDate,
			@RequestParam("queryidon") String queryidon,
			HttpServletRequest request) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		Wx_user user = (Wx_user) request.getSession().getAttribute("userinfo");
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
		String year = null;
		int month = 0;
		if (queryDate.split("年") != null && queryDate.split("年").length > 0) {
			year = queryDate.split("年")[0];
			if (queryDate.split("年")[1].split("月") != null && queryDate.split("年")[1].split("月").length > 0) {
				month = Integer.valueOf(queryDate.split("年")[1].split("月")[0].trim().toString()) - 1;
			}
		}
		String createapi_url = env.getProperty("createapi_url") + "idno=" + queryidon + "&year=" + year + "&month="
				+ month + ".json";
		;
		JSONObject salaryObject = WxAuthUtil.doGetJson(createapi_url);
		SalaryDetail salaryDetail = null;
		List<SalaryDetail> allSalaryDetail = new ArrayList<SalaryDetail>();
		if (salaryObject!=null&&salaryObject.getString("msg").equals("success")) {
			JSONArray jsonArray = salaryObject.getJSONArray("data");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject row = jsonArray.getJSONObject(i);
				salaryDetail = new SalaryDetail();
				row = jsonArray.getJSONObject(i);
				if (row.get("id") != null) {
					salaryDetail.setId(Integer.valueOf(row.get("id").toString()));
				}
				if (row.get("staffname") != null) {
					salaryDetail.setStaffname(row.get("staffname").toString());
				}
				if (row.get("position") != null) {
					salaryDetail.setPosition(row.get("position").toString());
				}
				if (row.get("hr") != null) {
					if (StringUtils.isNotBlank(row.get("hr").toString()) && !"null".equals(row.get("hr").toString())) {
						salaryDetail.setHr(Integer.valueOf(row.get("hr").toString()));
					}
				}
				if (row.get("hr_Str") != null) {
					salaryDetail.setHr_Str(row.get("hr_Str").toString());
				}
				if (row.get("idno") != null) {
					salaryDetail.setIdno(row.get("idno").toString());
				}
				if (row.get("entrydate") != null) {
					try {
						if (StringUtils.isNotBlank(row.get("entrydate").toString())
								&& !"null".equals(row.get("entrydate").toString())) {
							salaryDetail.setEntrydate(sft.parse(row.get("entrydate").toString()));
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (row.get("fullsalary") != null) {
					if (StringUtils.isNotBlank(row.get("fullsalary").toString())
							&& !"null".equals(row.get("fullsalary").toString())) {
						salaryDetail.setFullsalary(Float.valueOf(row.get("fullsalary").toString()));
					}
				}
				if (row.get("fullsalarydate") != null) {
					if (StringUtils.isNotBlank(row.get("fullsalarydate").toString())
							&& !"null".equals(row.get("fullsalarydate").toString())) {
						salaryDetail.setFullsalarydate(row.get("fullsalarydate").toString());
					}
				}
				if (row.get("paymentwage") != null) {
					if (StringUtils.isNotBlank(row.get("fullsalarydate").toString())
							&& !"null".equals(row.get("fullsalarydate").toString())) {
						salaryDetail.setPaymentwage(Float.valueOf(row.get("paymentwage").toString()));
					}
				}
				if (row.get("pcdeposit") != null) {
					salaryDetail.setPcdeposit(row.get("pcdeposit").toString());
				}
				if (row.get("insurancestatus") != null) {
					salaryDetail.setInsurancestatus(row.get("insurancestatus").toString());
				}
				if (row.get("payday") != null) {
					salaryDetail.setPayday(Float.valueOf(row.get("payday").toString()));
				}
				if (row.get("actualpayday") != null) {
					if (StringUtils.isNotBlank(row.get("actualpayday").toString())
							&& !"null".equals(row.get("actualpayday").toString())) {
						salaryDetail.setActualpayday(Float.valueOf(row.get("actualpayday").toString()));
					}
				}
				if (row.get("subsidydebit") != null) {
					if (StringUtils.isNotBlank(row.get("subsidydebit").toString())
							&& !"null".equals(row.get("subsidydebit").toString())) {
						salaryDetail.setSubsidydebit(Float.valueOf(row.get("subsidydebit").toString()));
					}
				}
				if (row.get("subsidydebitexplain") != null) {
					salaryDetail.setSubsidydebitexplain(row.get("subsidydebitexplain").toString());
				}
				if (row.get("reimbursement") != null) {
					if (StringUtils.isNotBlank(row.get("reimbursement").toString())
							&& !"null".equals(row.get("reimbursement").toString())) {
						salaryDetail.setReimbursement(Float.valueOf(row.get("reimbursement").toString()));
					}
				}
				if (row.get("socialsecurity") != null) {
					if (StringUtils.isNotBlank(row.get("socialsecurity").toString())
							&& !"null".equals(row.get("socialsecurity").toString())) {
						salaryDetail.setSocialsecurity(Float.valueOf(row.get("socialsecurity").toString()));
					}
				}
				if (row.get("accumulationfund") != null) {
					if (StringUtils.isNotBlank(row.get("accumulationfund").toString())
							&& !"null".equals(row.get("accumulationfund").toString())) {
						salaryDetail.setAccumulationfund(Float.valueOf(row.get("accumulationfund").toString()));
					}
				}
				if (row.get("wages") != null) {
					if (StringUtils.isNotBlank(row.get("wages").toString())
							&& !"null".equals(row.get("wages").toString())) {
						salaryDetail.setWages(Float.valueOf(row.get("wages").toString()));
					}
				}
				if (row.get("actualwages") != null) {
					if (StringUtils.isNotBlank(row.get("actualwages").toString())
							&& !"null".equals(row.get("actualwages").toString())) {
						salaryDetail.setActualwages(Float.valueOf(row.get("actualwages").toString()));
					}
				}
				if (row.get("selftax") != null) {
					if (StringUtils.isNotBlank(row.get("selftax").toString())
							&& !"null".equals(row.get("selftax").toString())) {
						salaryDetail.setSelftax(Float.valueOf(row.get("selftax").toString()));
					}
				}
				if (row.get("incometax") != null) {
					if (StringUtils.isNotBlank(row.get("incometax").toString())
							&& !"null".equals(row.get("incometax").toString())) {
						salaryDetail.setIncometax(Float.valueOf(row.get("incometax").toString()));
					}
				}
				if (row.get("shifagongzi") != null) {
					if (StringUtils.isNotBlank(row.get("shifagongzi").toString())
							&& !"null".equals(row.get("shifagongzi").toString())) {
						salaryDetail.setShifagongzi(Float.valueOf(row.get("shifagongzi").toString()));
					}
				}
				if (row.get("yifa") != null) {
					if (StringUtils.isNotBlank(row.get("yifa").toString())
							&& !"null".equals(row.get("yifa").toString())) {
						salaryDetail.setYifa(Float.valueOf(row.get("yifa").toString()));
					}
				}
				if (row.get("bufa") != null) {
					if (StringUtils.isNotBlank(row.get("bufa").toString())
							&& !"null".equals(row.get("bufa").toString())) {
						salaryDetail.setBufa(Float.valueOf(row.get("bufa").toString()));
					}
				}
				if (row.get("zhanghu") != null) {
					salaryDetail.setZhanghu(row.get("zhanghu").toString());
				}
				if (row.get("kaihuhangname") != null) {
					salaryDetail.setKaihuhangname(row.get("kaihuhangname").toString());
				}
				if (row.get("yl1") != null) {
					salaryDetail.setYl1(row.get("yl1").toString());
				}
				if (row.get("yl2") != null) {
					salaryDetail.setYl2(row.get("yl2").toString());
				}
				if (row.get("yl3") != null) {
					salaryDetail.setYl3(row.get("yl3").toString());
				}
				if (row.get("yl4") != null) {
					salaryDetail.setYl4(row.get("yl4").toString());
				}
				if (row.get("yl5") != null) {
					salaryDetail.setYl5(row.get("yl5").toString());
				}
				if (row.get("companyid") != null) {
					if (StringUtils.isNotBlank(row.get("companyid").toString())
							&& !"null".equals(row.get("companyid").toString())) {
						salaryDetail.setCompanyid(Integer.valueOf(row.get("companyid").toString()));
					}
				}
				if (row.get("createby") != null) {
					if (StringUtils.isNotBlank(row.get("createby").toString())
							&& !"null".equals(row.get("createby").toString())) {
						salaryDetail.setCreateby(Integer.valueOf(row.get("createby").toString()));
					}
				}
				allSalaryDetail.add(salaryDetail);
			}
		}
		request.setAttribute("allSalaryDetail", allSalaryDetail);
		return "wx/salary_month"; /** 返回的是显示数据的html的文件名 */
	}
}
