package com.example.pos_flutter.models;

public class PaymentResult {
    private String Status;
    private String TerminalID;
    private String STAN;
    private String RRN;
    private String ResponseCode;
    private String CustomerCardNO;
    private String TransactionDateTime;
    private String Description;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTerminalID() {
        return TerminalID;
    }

    public void setTerminalID(String terminalID) {
        TerminalID = terminalID;
    }

    public String getSTAN() {
        return STAN;
    }

    public void setSTAN(String STAN) {
        this.STAN = STAN;
    }

    public String getRRN() {
        return RRN;
    }

    public void setRRN(String RRN) {
        this.RRN = RRN;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getCustomerCardNO() {
        return CustomerCardNO;
    }

    public void setCustomerCardNO(String customerCardNO) {
        CustomerCardNO = customerCardNO;
    }

    public String getTransactionDateTime() {
        return TransactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        TransactionDateTime = transactionDateTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
