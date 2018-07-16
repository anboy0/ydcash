package com.demo.demo.sys.dto;

import com.demo.demo.sys.entity.BusCompany;

public class CompanyDto  extends BusCompany{

    private String siteName;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
