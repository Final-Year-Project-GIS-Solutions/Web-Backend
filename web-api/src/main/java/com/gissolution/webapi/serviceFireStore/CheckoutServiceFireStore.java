package com.gissolution.webapi.serviceFireStore;

import com.gissolution.webapi.output.Checkout;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firestore.v1.Write;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CheckoutServiceFireStore {

    private String collectionName = "checkout";

    public String postCheckout(Checkout checkout) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> writeResultApiFuture = firestore.collection(collectionName).document(checkout.getCheckoutId()).set(checkout);
        return writeResultApiFuture.get().getUpdateTime().toString();
    }

    public GenericResponse<List<Checkout>> getCheckout() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        List<Checkout> checkouts = new ArrayList<>();
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = firestore.collection(collectionName).get();
        for (DocumentSnapshot documentSnapshot :querySnapshotApiFuture.get().getDocuments()){
            checkouts.add(documentSnapshot.toObject(Checkout.class));
        }

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponse(checkouts);
        genericResponse.setError(false);
        genericResponse.setHasMorePage(false);
        return genericResponse;
    }
}
