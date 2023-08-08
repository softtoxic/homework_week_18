package com.bestbuy.constant;

public class EndPoints {
    // Products End points
    public static final String CREATE_PRODUCTS = "/products";
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String GET_PRODUCT_BY_ID = "/{productID}";
    public static final String DELETE_PRODUCT_BY_ID = "/{productID}";
    public static final String UPDATE_PRODUCT_BY_ID= "/{productID}";
     //Stores End points
    public static final String CREATE_STORES = "/stores";
    public static final String GET_ALL_STORES = "/stores";
    public static final String GET_STORES_BY_ID = "/{storeID}";
    public static final String DELETE_STORE_BY_ID = "/{storeID}";
    public static final String UPDATE_STORE_BY_ID= "/{storeID}";
}
