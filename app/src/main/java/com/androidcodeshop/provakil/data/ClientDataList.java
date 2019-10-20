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
            ClientDetailsModel clientDetailsModel ;
            for(int i = 0 ; i < 5 ; i++) {
                clientDetailsModel = new ClientDetailsModel();
                clientDetailsModel.setmFirstName("Demo");
                clientDetailsModel.setmLastName("Prashad "+i);
                clientDetailsModel.setmContactNumber("998877664"+i);
                clientDetailsModel.setmClientCode("29HGJKL30"+i);
                storedData.add(clientDetailsModel);
            }
            return storedData;
        }
        return storedData;
    }

}
