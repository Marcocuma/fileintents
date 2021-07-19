package es.everyware.plugins.fileintents;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;

import androidx.core.content.ContentResolverCompat;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

@NativePlugin(name = "FileIntents")
public class FileIntents extends Plugin {


    @PluginMethod
    public void checkIntentReceived(PluginCall call) throws IOException {

        Intent intent = bridge.getActivity().getIntent();
        System.out.println(intent);
        if (intent != null && intent.getData() != null) {
            JSObject ret = new JSObject();

            try {
                System.out.println(getContext().getFilesDir());
                File destination =new File(getContext().getFilesDir().getAbsolutePath()+"/"+System.currentTimeMillis()+".ifh");
                try (InputStream input = getContext().getContentResolver().openInputStream(intent.getData())) {
                    try (OutputStream out = new FileOutputStream(destination)) {
                        // Transfer bytes from in to out
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = input.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        if(destination.exists()){
                            ret.put("data", destination.getName());
                            call.resolve(ret);
                        } else {
                            if(destination.createNewFile()){
                                ret.put("data", destination.getName());
                                call.resolve(ret);
                            }
                            System.out.println(destination.getAbsolutePath());
                            call.reject("Error writing file");
                        }
                    }
                }
            } catch( FileNotFoundException e2 ) {
                call.reject("Error proccessing");
                e2.printStackTrace();
            }
        } else {
            call.reject("No processing needed");
        }
    }


}
