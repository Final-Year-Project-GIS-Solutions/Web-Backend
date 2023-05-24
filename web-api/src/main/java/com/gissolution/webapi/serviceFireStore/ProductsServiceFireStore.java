package com.gissolution.webapi.serviceFireStore;

import com.gissolution.webapi.output.ProductDemo;
import com.gissolution.webapi.output.Products;
import com.gissolution.webapi.output.WareHouse;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductsServiceFireStore {
    private String collectionName = "products";

    public String postProducts(ProductDemo productDemo) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(collectionName).document(productDemo.getProductId()).set(productDemo);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public GenericResponse<List<ProductDemo>> getProducts() throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();

        List<ProductDemo> productDemos = new ArrayList<>();
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFireStore.collection(collectionName).get();
        for (DocumentSnapshot documentSnapshot :querySnapshotApiFuture.get().getDocuments()) {
            productDemos.add(documentSnapshot.toObject(ProductDemo.class));
        }

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponse(productDemos);
        genericResponse.setError(false);
        genericResponse.setHasMorePage(false);
        return genericResponse;
    }
}
