package cn.pogotravel.shihe.provider;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.OnProgressListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

@Service
public class UcloudProvider {
    @Value("${ucloud.ufile.public-key}")
    private String publicKey;

    @Value("${ucloud.ufile.private-key}")
    private String privateKey;


    public String upload(InputStream fileStream,String mimeType,String fileName){
        File file = new File("your file path");
        String generateFileName="";
        String[] fileSpliter= fileName.split(",");
        if (fileSpliter.length>1){
            generateFileName= UUID.randomUUID().toString()+"."+fileSpliter[fileSpliter.length-1];
        }

        try {
            ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(publicKey, privateKey);
            //ObjectConfig config = new ObjectConfig("cos.ap-chongqing", "myqcloud.com");
            ObjectConfig config = new ObjectConfig("https://shihe-1259080748.cos.ap-chongqing.myqcloud.com");
            PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
                    .putObject(fileStream, mimeType)
                    .nameAs(fileName)
                    .toBucket("shihe")
                    .setOnProgressListener(new OnProgressListener() {
                        @Override
                        public void onProgress(long bytesWritten, long contentLength) {}
                    })
                    .execute();
        } catch (UfileClientException e) {
            e.printStackTrace();
        } catch (UfileServerException e) {
            e.printStackTrace();
        }
        return generateFileName;
    }

}
