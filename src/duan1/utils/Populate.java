package duan1.utils;

import java.util.ArrayList;

import org.bson.Document;

import duan1.interfaces.IModel;

public class Populate<M extends IModel> {
    public M find(String _id, ArrayList<M> arr) {
        for(int i = 0; i < arr.size(); i++) {
            if(arr.get(i).get("_id").toString().equals(_id)) {
                return arr.get(i);
            }
        }

        return null;
    }
}
