/*
 * Copyright (C) 2013 TinhVan Outsourcing.
 */
package com.dtcs.slldt.webservice;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import android.content.Context;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class JsonUtil.
 */
public class JsonUtil {

    /**
     * convert a object from a json string.
     *
     * @param <T> the generic type
     * @param json the json
     * @param returnType the return type
     * @return the t
     */
    public static <T> T fromString(String json, Class<T> returnType) {
        T ret = null;
        if (json != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            try {
                ret = mapper.readValue(json, returnType);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
    
    /**
     * From string.
     *
     * @param <T> the generic type
     * @param json the json
     * @param type the type
     * @return the t
     */
    public static <T> T fromString(String json,@SuppressWarnings("rawtypes") TypeReference type) {
        T var = null;
        if (json != null) {
            Log.d("JsonUntil", json);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            
            try {
               var = mapper.readValue(json, type);
                
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return var;
    }
    
    /**
     * convert a object from a json string.
     *
     * @param <T> the generic type
     * @param json the json
     * @param returnType the return type
     * @return the t
     */
    public static <T> T convertObjectFromJsonString(String json, Class<T> returnType) {
        T ret = null;
        if (json != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            try {
                ret = mapper.readValue(json, returnType);
            } catch (JsonParseException e) {
                e.printStackTrace();
                return null;
            } catch (JsonMappingException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return ret;
    }
    
    /**
     * Convert array list from json string.
     *
     * @param <T> the generic type
     * @param json the json
     * @param item_class the item_class
     * @return the array list
     */
    public static <T> ArrayList<T> convertArrayListFromJsonString(String json, Class<T> item_class) {
        if(json == null)
            return null;
        
        ArrayList<T> ret = null;
        ObjectMapper mapper = new ObjectMapper();
        
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, item_class);
            ret = mapper.readValue(json, type);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ret;
    }

    /**
     * From file.
     *
     * @param <T> the generic type
     * @param file the file
     * @param returnType the return type
     * @return the t
     */
    public static <T> T fromFile(File file, Class<T> returnType) {
        T ret = null;
        if (file.exists()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            try {
                ret = mapper.readValue(file, returnType);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * From string to array.
     *
     * @param <T> the generic type
     * @param json the json
     * @param item_class the item_class
     * @return the array list
     */
    public static <T> ArrayList<T> fromStringToArray(String json, Class<T> item_class) {
        if (json == null) return null;
        ArrayList<T> ret = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            ret = mapper.readValue(json, new TypeReference<ArrayList<T>>() {});
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ret;
    }

    /**
     * Write string.
     *
     * @param o the o
     * @return the string
     */
    public static String writeString(Object o) {
        String ret = null;
        if (o != null) {
            ObjectMapper om = new ObjectMapper();
            try {
                ret = om.writeValueAsString(o);
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * Get String from file json in asset folder.
     *
     * @param context the context
     * @param name of file in asset such as "name.json" or "name.txt"
     * @return string json.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String getStringFromAsset(Context context, String name) throws IOException {
        InputStream input;
        input = context.getAssets().open(name);
        int size = input.available();
        byte[] buffer = new byte[size];
        input.read(buffer);
        input.close();

        // byte buffer into a string
        String jsonString = new String(buffer);
        return jsonString;
    }
}
