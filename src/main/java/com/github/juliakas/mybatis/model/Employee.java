package com.github.juliakas.mybatis.model;

public class Employee {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.EMPLOYEES.EMP_ID
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    private Long empId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.EMPLOYEES.FIRST_NAME
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    private String firstName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.EMPLOYEES.LAST_NAME
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    private String lastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.EMPLOYEES.COMP_ID
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    private Long compId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.EMPLOYEES.MANAGER_ID
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    private Long managerId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.EMPLOYEES.EMP_ID
     *
     * @return the value of PUBLIC.EMPLOYEES.EMP_ID
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.EMPLOYEES.EMP_ID
     *
     * @param empId the value for PUBLIC.EMPLOYEES.EMP_ID
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.EMPLOYEES.FIRST_NAME
     *
     * @return the value of PUBLIC.EMPLOYEES.FIRST_NAME
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.EMPLOYEES.FIRST_NAME
     *
     * @param firstName the value for PUBLIC.EMPLOYEES.FIRST_NAME
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.EMPLOYEES.LAST_NAME
     *
     * @return the value of PUBLIC.EMPLOYEES.LAST_NAME
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.EMPLOYEES.LAST_NAME
     *
     * @param lastName the value for PUBLIC.EMPLOYEES.LAST_NAME
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.EMPLOYEES.COMP_ID
     *
     * @return the value of PUBLIC.EMPLOYEES.COMP_ID
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public Long getCompId() {
        return compId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.EMPLOYEES.COMP_ID
     *
     * @param compId the value for PUBLIC.EMPLOYEES.COMP_ID
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public void setCompId(Long compId) {
        this.compId = compId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.EMPLOYEES.MANAGER_ID
     *
     * @return the value of PUBLIC.EMPLOYEES.MANAGER_ID
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public Long getManagerId() {
        return managerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.EMPLOYEES.MANAGER_ID
     *
     * @param managerId the value for PUBLIC.EMPLOYEES.MANAGER_ID
     *
     * @mbg.generated Sat Apr 11 13:09:36 EEST 2020
     */
    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}