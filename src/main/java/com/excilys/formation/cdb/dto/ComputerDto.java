package com.excilys.formation.cdb.dto;

public class ComputerDto {

    private long computerId;
    private String computerName;
    private String computerIntroduced;
    private String computerDiscontinued;
    private String computerCompanyName;

    public long getComputerId() {
        return computerId;
    }
    public void setComputerId(long computerId) {
        this.computerId = computerId;
    }

    public String getComputerName() {
        return computerName;
    }
    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getComputerIntroduced() {
        return computerIntroduced;
    }
    public void setComputerIntroduced(String computerIntroduced) {
        this.computerIntroduced = computerIntroduced;
    }

    public String getComputerDiscontinued() {
        return computerDiscontinued;
    }
    public void setComputerDiscontinued(String computerDiscontinued) {
        this.computerDiscontinued = computerDiscontinued;
    }
    
    public String getComputerCompanyName() {
        return computerCompanyName;
    }
    public void setComputerCompany(String computerCompanyName) {
        this.computerCompanyName = computerCompanyName;
    }

}
