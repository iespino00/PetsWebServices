package com.iespino.pets.restApi;

public class ConstantesRestApi
{
    // https://api.instagram.com/v1
    public final static String VERSION = "/v1/";
    public final static String ROOT_URL = "https://api.instagram.com" + VERSION;
    public final static String ACCESS_TOKEN = "3439512068.30aa767.4f7203ba3534435f87910315db187e1d";
    public final static String KEY_ACCESS_TOKEN = "?access_token=";
    public final static String KEY_GET_INFORMATION_USER = "users/self/media/recent/";
    public final static String USER_ID_MEDIA_RECENT = "users/{user-id}/";
    //VARIABLE DE LA URL COMPLETA PARA OBTENER LAS FOTOS RECIENTES DEL USUARIO
    //NO SE PONDRA LA ROOT URL NI LA VERSION PORQUE LUEGO SE HARA CON RETROFIT

    // https://api.instagram.com/v1/users/self/?access_token=ACCESS-TOKEN
    public final static String KEY_GET_PROFILE_USER = "users/self/";

   // https://api.instagram.com/v1/users/{user-id}/?access_token=ACCESS-TOKEN
    public final static String URL_GET_PROFILE_USER = KEY_GET_PROFILE_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public final static String KEY_GET_FOLLOWS      =    "follows";

    //Obtengo los seguidores
   // https://api.instagram.com/v1/users/self/follows?access_token=ACCESS-TOKEN
    public final static String URL_GET_FOLLOWS = KEY_GET_PROFILE_USER + KEY_GET_FOLLOWS + KEY_ACCESS_TOKEN +ACCESS_TOKEN;

    //Obtener los medios recientes por id de usuario
    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    public static final String KEY_GET_USERS= "users/{user-id}/media/recent/";
    public final static String URL_GET_FOLOWS_MEDIA_RECENT = KEY_GET_USERS +KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //  https://api.instagram.com/v1/users/{user-id}/?access_token=ACCESS-TOKEN
    public final static String URL_GET_PROFILE_USER_WITH_ID = USER_ID_MEDIA_RECENT + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

}
