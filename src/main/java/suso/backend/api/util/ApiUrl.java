package suso.backend.api.util;

public class ApiUrl {
    public static final String USER_JOIN = "/join";
    public static final String USER_LOGIN = "/login";
    public static final String USER_ROOT = "/user";
    public static final String USER_GET = USER_ROOT+"/get";

    public static final String CERTIFICATES_ROOT = "/certificates";
    public static final String CERTIFICATES_ID = "/{id}";
    public static final String CERTIFICATES_SAVE = CERTIFICATES_ROOT + "/save";
    public static final String CERTIFICATES_DELETE = CERTIFICATES_ROOT + "/delete";
    public static final String CERTIFICATES_UPDATE = CERTIFICATES_ROOT + "/update" + CERTIFICATES_ID;

    public static final String HASH_ROOT = "/hash";
    public static final String HASH_RANK = HASH_ROOT + "/rank";
    public static final String HASH_CERTIFICATES = HASH_ROOT + "/certificates";

}
