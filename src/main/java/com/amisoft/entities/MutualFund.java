package com.amisoft.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_mutual_fund")
public class MutualFund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String schemaCode;
    private String schemaName;
    private String nav;
    private String fundManager;
    private String fundType;
    private String growthOrDiv;
    private String date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchemaCode() {
        return schemaCode;
    }

    public void setSchemaCode(String schemaCode) {
        this.schemaCode = schemaCode;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getFundManager() {
        return fundManager;
    }

    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getGrowthOrDiv() {
        return growthOrDiv;
    }

    public void setGrowthOrDiv(String growthOrDiv) {
        this.growthOrDiv = growthOrDiv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
