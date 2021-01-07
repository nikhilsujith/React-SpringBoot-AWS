package tech.nikhilsujith.reactspringaws.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {

    private final AmazonS3 s3;

    @Autowired //dependency injection
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }


/*Save Method*/
    public void save(String path, //path for the image
                           String fileName,  //name of the image file
                           Optional<Map<String, String>> optionalMetadata,  //optional additional metadata about the image
                           InputStream inputStream ){//Stream serves as a conduit..... i think

                                ObjectMetadata metadata = new ObjectMetadata();
                                optionalMetadata.ifPresent(map -> {
                                    if(!map.isEmpty()) {
                                        map.forEach((key, value) -> metadata.addUserMetadata(key, value));
                                        /*  Preferred clean way to write the above line is
                                        * map.forEach(objectMetadata::addUserMetadata)
                                        * this is called method reference*/
                                    }
                                });

                                try {
                                    s3.putObject(path, fileName, inputStream, metadata);
                                } catch (AmazonServiceException e){
                                    throw new IllegalStateException("Failed to store file to s3", e);
                                }
                            }

    public byte[] download(String path, String key) {
        try{
            S3Object object = s3.getObject(path,key);
            S3ObjectInputStream inputStream = object.getObjectContent();
            return IOUtils.toByteArray(inputStream);
        } catch (AmazonServiceException | IOException e){
            throw new IllegalStateException("Failed to download file to s3", e);
        }
    }
}
