package com.example.checkout_service.api.client;

import java.util.List;

public interface Loadable<T> {

    public List<T> loadData();

}
