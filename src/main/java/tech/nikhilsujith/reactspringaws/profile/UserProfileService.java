package tech.nikhilsujith.reactspringaws.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.nikhilsujith.reactspringaws.bucket.BucketName;
import tech.nikhilsujith.reactspringaws.filestore.FileStore;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;


@Service
public class UserProfileService  {

    private final UserProfileServiceDataAccessService userProfileServiceDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileServiceDataAccessService userProfileServiceDataAccessService, FileStore fileStore) {
        this.userProfileServiceDataAccessService = userProfileServiceDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileServiceDataAccessService.getUserProfiles();
    }


    /* Receive the userProfileId and file being sent from the client; (receiving from UserProfileController.java)*/
    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
//
//        *   1. Check if image is not empty
        isFileEmpty(file);
//        *   2. Check is file is an image
        isImage(file);

//        *   3. Whether user exists in database
        UserProfile user = getUserProfileOrThrow(userProfileId);

//        *   4. If exists, grab some metadata from file if any
        Map<String, String> metadata = extractMetadata(file);

//        *   5. Store image in S3 and update database with S3 image and link

//            Create folder for each user in S3
                String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileID());

                String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

                try{
                    fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
                }
                catch (IOException e){
                    throw new IllegalStateException(e);
                }


    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        return userProfileServiceDataAccessService //Extracting all stream details into variable user
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileID().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User Profile %s not found", userProfileId)));
    }

    private void isImage(MultipartFile file) {
        if(!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("File must be an image");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("Cannot load empty file [ " + file.getSize() + "]" );
        }
    }
}
