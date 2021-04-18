package com.example.firebaseuploadimage;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;

public class Utility {

    public static String getFileExtension(Context context, Uri uri) {
        ContentResolver cr=context.getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
}
