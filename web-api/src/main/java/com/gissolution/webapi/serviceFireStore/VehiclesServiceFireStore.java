package com.gissolution.webapi.serviceFireStore;

import com.gissolution.webapi.output.Vehicles;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class VehiclesServiceFireStore {
    private String collectionName = "vehicles";
    public String postVehicles(Vehicles vehicles) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(collectionName).document(vehicles.getVehicleId()).set(vehicles);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public GenericResponse<List<Vehicles>> getVehicles() throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();

        List<Vehicles> vehicles = new ArrayList<>();
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFireStore.collection(collectionName).get();
        for (DocumentSnapshot documentSnapshot :querySnapshotApiFuture.get().getDocuments()){
            vehicles.add(documentSnapshot.toObject(Vehicles.class));
        }

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setError(false);
        genericResponse.setHasMorePage(false);
        genericResponse.setResponse(vehicles);
        return genericResponse;
    }
}
