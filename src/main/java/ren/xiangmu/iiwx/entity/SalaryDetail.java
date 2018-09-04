package ren.xiangmu.iiwx.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryDetail {
	
    private Integer id;

    private String staffname;

    private String position;

    private Integer hr;
    
    private String hr_Str;

    private String idno;

    private Date entrydate;
    
    private String entrydateStr;

    private Float fullsalary;

    private String fullsalarydate;

    private Float paymentwage;

    private String pcdeposit;

    private String insurancestatus;

    private Float payday;

    private Float actualpayday;

    private Float subsidydebit;

    private String subsidydebitexplain;

    private Float reimbursement;

    private Float socialsecurity;

    private Float accumulationfund;

    private Float wages;

    private Float actualwages;

    private Float selftax;
    
    private Float incometax;
    

    private Float shifagongzi;

    private Float yifa;

    private Float bufa;

    private String zhanghu;

    private String kaihuhangname;

    private String yl1;

    private String yl2;
//岗位
    private String yl3;

    private String yl4;
    
    private String yl5;

    private Integer companyid;

    private Integer createby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname == null ? null : staffname.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Integer getHr() {
        return hr;
    }

    public void setHr(Integer hr) {
        this.hr = hr;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno == null ? null : idno.trim();
    }

    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    public Float getFullsalary() {
        return fullsalary;
    }

    public void setFullsalary(Float fullsalary) {
        this.fullsalary = fullsalary;
    }

    public String getFullsalarydate() {
        return fullsalarydate;
    }

    public void setFullsalarydate(String fullsalarydate) {
        this.fullsalarydate = fullsalarydate;
    }

    public Float getPaymentwage() {
        return paymentwage;
    }

    public void setPaymentwage(Float paymentwage) {
        this.paymentwage = paymentwage;
    }

    public String getPcdeposit() {
        return pcdeposit;
    }

    public void setPcdeposit(String pcdeposit) {
        this.pcdeposit = pcdeposit;
    }

    public String getInsurancestatus() {
        return insurancestatus;
    }

    public void setInsurancestatus(String insurancestatus) {
        this.insurancestatus = insurancestatus == null ? null : insurancestatus.trim();
    }

    public Float getPayday() {
        return payday;
    }

    public void setPayday(Float payday) {
        this.payday = payday;
    }

    public Float getActualpayday() {
        return actualpayday;
    }

    public void setActualpayday(Float actualpayday) {
        this.actualpayday = actualpayday;
    }

    public Float getSubsidydebit() {
        return subsidydebit;
    }

    public void setSubsidydebit(Float subsidydebit) {
        this.subsidydebit = subsidydebit;
    }

    public String getSubsidydebitexplain() {
        return subsidydebitexplain;
    }

    public void setSubsidydebitexplain(String subsidydebitexplain) {
        this.subsidydebitexplain = subsidydebitexplain == null ? null : subsidydebitexplain.trim();
    }

    public Float getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(Float reimbursement) {
        this.reimbursement = reimbursement;
    }

    public Float getSocialsecurity() {
        return socialsecurity;
    }

    public void setSocialsecurity(Float socialsecurity) {
        this.socialsecurity = socialsecurity;
    }

    public Float getAccumulationfund() {
        return accumulationfund;
    }

    public void setAccumulationfund(Float accumulationfund) {
        this.accumulationfund = accumulationfund;
    }

    public Float getWages() {
        return wages;
    }

    public void setWages(Float wages) {
        this.wages = wages;
    }

    public Float getActualwages() {
        return actualwages;
    }

    public void setActualwages(Float actualwages) {
        this.actualwages = actualwages;
    }

    public Float getSelftax() {
        return selftax;
    }

    public void setSelftax(Float selftax) {
        this.selftax = selftax;
    }

    public Float getShifagongzi() {
        return shifagongzi;
    }

    public void setShifagongzi(Float shifagongzi) {
        this.shifagongzi = shifagongzi;
    }

    public Float getYifa() {
        return yifa;
    }

    public void setYifa(Float yifa) {
        this.yifa = yifa;
    }

    public Float getBufa() {
        return bufa;
    }

    public void setBufa(Float bufa) {
        this.bufa = bufa;
    }

    public String getZhanghu() {
        return zhanghu;
    }

    public void setZhanghu(String zhanghu) {
        this.zhanghu = zhanghu == null ? null : zhanghu.trim();
    }

    public String getKaihuhangname() {
        return kaihuhangname;
    }

    public void setKaihuhangname(String kaihuhangname) {
        this.kaihuhangname = kaihuhangname == null ? null : kaihuhangname.trim();
    }

    public String getYl1() {
        return yl1;
    }

    public void setYl1(String yl1) {
        this.yl1 = yl1 == null ? null : yl1.trim();
    }

    public String getYl2() {
        return yl2;
    }

    public void setYl2(String yl2) {
        this.yl2 = yl2 == null ? null : yl2.trim();
    }

    public String getYl3() {
        return yl3;
    }

    public void setYl3(String yl3) {
        this.yl3 = yl3 == null ? null : yl3.trim();
    }

    public String getYl4() {
        return yl4;
    }

    public void setYl4(String yl4) {
        this.yl4 = yl4 == null ? null : yl4.trim();
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

	public Float getIncometax() {
		return incometax;
	}

	public void setIncometax(Float incometax) {
		this.incometax = incometax;
	}

	public String getEntrydateStr() {
		return entrydateStr;
	}

	public void setEntrydateStr(String entrydateStr) {
		this.entrydateStr = entrydateStr;
	}

	public String getHr_Str() {
		return hr_Str;
	}

	public void setHr_Str(String hr_Str) {
		this.hr_Str = hr_Str;
	}

	public String getYl5() {
		return yl5;
	}

	public void setYl5(String yl5) {
		this.yl5 = yl5;
	}


}