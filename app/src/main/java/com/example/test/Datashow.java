package com.example.test;

/**
 * Created by 潘俊杰 on 2018/9/6.
 */

public class Datashow {
    private String pileno;
    private String testtype;
    private String recordcount;
    private String starttime;
    private String createtime;
    private String updatetime;
    private String creatername;


    public Datashow(String pileno, String testtype, String recordcount, String starttime, String createtime, String updatetime, String creatername) {
        this.pileno = pileno;
        this.testtype = testtype;
        this.recordcount = recordcount;
        this.starttime = starttime;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.creatername = creatername;
    }

    public String getPileno() {
        return pileno;
    }

    public void setPileno(String pileno) {
        this.pileno = pileno;
    }

    public String getTesttype() {
        return testtype;
    }

    public void setTesttype(String testtype) {
        this.testtype = testtype;
    }

    public String getRecordcount() {
        return recordcount;
    }

    public void setRecordcount(String recordcount) {
        this.recordcount = recordcount;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getCreatername() {
        return creatername;
    }

    public void setCreatername(String creatername) {
        this.creatername = creatername;
    }

    @Override
    public String toString() {
        return "Datashow{" +
                "pileno='" + pileno + '\'' +
                ", testtype='" + testtype + '\'' +
                ", recordcount='" + recordcount + '\'' +
                ", starttime='" + starttime + '\'' +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", creatername='" + creatername + '\'' +
                '}';
    }
}
