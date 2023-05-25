package com.gissolution.webapi.serviceFireStore;

import com.gissolution.webapi.output.WareHouse;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.protobuf.Api;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class WarehouseServiceFireStore {
    private String collectionName = "warehouse";

    public String postWarehouse(WareHouse wareHouse) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(collectionName).document(wareHouse.getId()).set(wareHouse);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public GenericResponse<List<WareHouse>> getWarehouse() throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();

        List<WareHouse> wareHouses = new ArrayList<>();
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFireStore.collection(collectionName).get();
        for (DocumentSnapshot documentSnapshot :querySnapshotApiFuture.get().getDocuments()){
            wareHouses.add(documentSnapshot.toObject(WareHouse.class));
        }

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponse(wareHouses);
        genericResponse.setError(false);
        genericResponse.setHasMorePage(false);
        return genericResponse;
    }
}
