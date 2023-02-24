package com.example.pos_flutter.models;

public class PaymentData {
    private String AndroidPosMessageHeader;
    private String ECRType;
    private String Amount;
    private String TransactionType;
    private String BillNo;
    private String AdditionalData;
    private String OriginalAmount;
    private String SwipeCardTimeout;
    private String ReceiptType;

    public String getAndroidPosMessageHeader() {
        return AndroidPosMessageHeader;
    }

    public void setAndroidPosMessageHeader(String androidPosMessageHeader) {
        AndroidPosMessageHeader = androidPosMessageHeader;
    }

    public String getECRType() {
        return ECRType;
    }

    public void setECRType(String ECRType) {
        this.ECRType = ECRType;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String billNo) {
        BillNo = billNo;
    }

    public String getAdditionalData() {
        return AdditionalData;
    }

    public void setAdditionalData(String additionalData) {
        AdditionalData = additionalData;
    }

    public String getOriginalAmount() {
        return OriginalAmount;
    }

    public void setOriginalAmount(String originalAmount) {
        OriginalAmount = originalAmount;
    }

    public String getSwipeCardTimeout() {
        return SwipeCardTimeout;
    }

    public void setSwipeCardTimeout(String swipeCardTimeout) {
        SwipeCardTimeout = swipeCardTimeout;
    }

    public String getReceiptType() {
        return ReceiptType;
    }

    public void setReceiptType(String receiptType) {
        ReceiptType = receiptType;
    }
}
