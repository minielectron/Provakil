package com.androidcodeshop.provakil.datamodels;

import com.androidcodeshop.provakil.datamodels.AddressType;

public class ClientDetailsModel {

    private String mFirstName;
    private String mLastName;
    private String mClientCode;
    private String mAddressLineOne;
    private String mAddressLineTwo;
    private String mCity;
    private String mState;
    private String mPin;
    private AddressType mAddressType;
    private AddressType mContactType;
    private String mContactNumber;
    private String mOperationalState;
    private String mExtraDetails;

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmClientCode() {
        return mClientCode;
    }

    public void setmClientCode(String mClientCode) {
        this.mClientCode = mClientCode;
    }

    public String getmAddressLineOne() {
        return mAddressLineOne;
    }

    public void setmAddressLineOne(String mAddressLineOne) {
        this.mAddressLineOne = mAddressLineOne;
    }

    public String getmAddressLineTwo() {
        return mAddressLineTwo;
    }

    public void setmAddressLineTwo(String mAddressLineTwo) {
        this.mAddressLineTwo = mAddressLineTwo;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmPin() {
        return mPin;
    }

    public void setmPin(String mPin) {
        this.mPin = mPin;
    }

    public AddressType getmAddressType() {
        return mAddressType;
    }

    public void setmAddressType(AddressType mAddressType) {
        this.mAddressType = mAddressType;
    }

    public AddressType getmContactType() {
        return mContactType;
    }

    public void setmContactType(AddressType mContactType) {
        this.mContactType = mContactType;
    }

    public String getmContactNumber() {
        return mContactNumber;
    }

    public void setmContactNumber(String mContactNumber) {
        this.mContactNumber = mContactNumber;
    }

    public String getmOperationalState() {
        return mOperationalState;
    }

    public void setmOperationalState(String mOperationalState) {
        this.mOperationalState = mOperationalState;
    }

    public String getmExtraDetails() {
        return mExtraDetails;
    }

    public void setmExtraDetails(String mExtraDetails) {
        this.mExtraDetails = mExtraDetails;
    }

    public String getName(){
        return getmFirstName() + " " + getmLastName();
    }
}
