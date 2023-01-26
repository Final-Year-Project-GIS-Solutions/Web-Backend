package com.gissolution.webapi.serviceFireStore;

import com.gissolution.webapi.output.Categories;
import com.gissolution.webapi.output.generic.GenericResponse;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryServiceFireStore {
    private String collectionName = "categories";
    public String postCategories(Categories categories) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(collectionName).document(categories.getCategoryId()).set(categories);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public GenericResponse<List<Categories>> getCategories() throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();

        List<Categories> categories = new ArrayList<>();
        ApiFuture<QuerySnapshot> queryDocumentSnapshotApiFuture = dbFireStore.collection(collectionName).get();
        for (DocumentSnapshot documentSnapshot :queryDocumentSnapshotApiFuture.get().getDocuments()) {
            categories.add(documentSnapshot.toObject(Categories.class));
        }


        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setError(false);
        genericResponse.setHasMorePage(false);
        genericResponse.setResponse(categories);

        return genericResponse;
    }
}
