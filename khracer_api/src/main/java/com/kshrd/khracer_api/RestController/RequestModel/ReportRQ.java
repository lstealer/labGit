package com.kshrd.khracer_api.RestController.RequestModel;

public class ReportRQ {
    private int id;
    private String reportText;
    public ReportRQ(){}
    public ReportRQ(int id, String reportText) {
        this.id = id;
        this.reportText = reportText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }
}
