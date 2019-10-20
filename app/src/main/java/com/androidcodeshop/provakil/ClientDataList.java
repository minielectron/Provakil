package com.androidcodeshop.provakil;

import java.util.ArrayList;

public class ClientDataList {

    private static ArrayList<ClientDetailsModel> storedData;

    private ClientDataList() {
    }

    public static ArrayList<ClientDetailsModel> getStoredData() {
        if (storedData == null) {
            storedData = new ArrayList<>();
            return storedData;
        }
        return storedData;
    }

}
