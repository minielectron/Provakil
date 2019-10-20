package com.androidcodeshop.provakil.data;

import com.androidcodeshop.provakil.datamodels.ClientDetailsModel;

import java.util.ArrayList;

public class ClientDataList {

    private static ArrayList<ClientDetailsModel> storedData;

    private ClientDataList() {
    }

    //Singleton pattern level1
    public static ArrayList<ClientDetailsModel> getStoredData() {
        if (storedData == null) {
            storedData = new ArrayList<>();
            return storedData;
        }
        return storedData;
    }

}
