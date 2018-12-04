package az.riberry.aescrypto;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AesCryptoModule extends ReactContextBaseJavaModule {
    public AesCryptoModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RBRAesCrypto";
    }

    byte[] fullyReadFileToBytes(File f) throws IOException {
        int size = (int) f.length();
        byte bytes[] = new byte[size];
        byte tmpBuff[] = new byte[size];
        FileInputStream fis= new FileInputStream(f);
        try {

            int read = fis.read(bytes, 0, size);
            if (read < size) {
                int remain = size - read;
                while (remain > 0) {
                    read = fis.read(tmpBuff, 0, remain);
                    System.arraycopy(tmpBuff, 0, bytes, size - remain, read);
                    remain -= read;
                }
            }
        }  catch (IOException e){
            throw e;
        } finally {
            fis.close();
        }

        return bytes;
    }

    @ReactMethod
    public void decryptAESFile(String fSrc, String fDst, String secretKey, String ivParameter, Promise promise) {
        try {
            if (secretKey == null && "".equals(secretKey)) {
                Log.e("secretKey====>>>>", "secretKey is null");
                promise.reject("INVALID_PARAMETER", "secretKey MUST not be null");
            }
            if (ivParameter == null && "".equals(ivParameter)) {
                Log.e("ivParameter====>>>>", "ivParameter is null");
                promise.reject("INVALID_PARAMETER", "ivParameter MUST not be null");
                return;
            }
            byte[] raw = secretKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] encrypted1 = fullyReadFileToBytes(new File(fSrc));

            byte[] original = cipher.doFinal(encrypted1);

            FileOutputStream out = new FileOutputStream(new File(fDst));
            out.write(original);
            out.close();

            promise.resolve(fDst);
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject(e);
        }
    }
}
