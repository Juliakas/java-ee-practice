package com.github.juliakas.mybatis.model;

public class Company {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.COMPANIES.COMP_ID
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    private Long compId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.COMPANIES.COMP_REG_NO
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    private Long compRegNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.COMPANIES.NAME
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.COMPANIES.COMP_ID
     *
     * @return the value of PUBLIC.COMPANIES.COMP_ID
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public Long getCompId() {
        return compId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.COMPANIES.COMP_ID
     *
     * @param compId the value for PUBLIC.COMPANIES.COMP_ID
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public void setCompId(Long compId) {
        this.compId = compId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.COMPANIES.COMP_REG_NO
     *
     * @return the value of PUBLIC.COMPANIES.COMP_REG_NO
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public Long getCompRegNo() {
        return compRegNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.COMPANIES.COMP_REG_NO
     *
     * @param compRegNo the value for PUBLIC.COMPANIES.COMP_REG_NO
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public void setCompRegNo(Long compRegNo) {
        this.compRegNo = compRegNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.COMPANIES.NAME
     *
     * @return the value of PUBLIC.COMPANIES.NAME
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.COMPANIES.NAME
     *
     * @param name the value for PUBLIC.COMPANIES.NAME
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public void setName(String name) {
        this.name = name;
    }
}