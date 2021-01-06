package tech.nikhilsujith.reactspringaws.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class UserProfileService  {

    private final UserProfileServiceDataAccessService userProfileServiceDataAccessService;

    @Autowired
    public UserProfileService(UserProfileServiceDataAccessService userProfileServiceDataAccessService) {
        this.userProfileServiceDataAccessService = userProfileServiceDataAccessService;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileServiceDataAccessService.getUserProfiles();
    }


    /* Receive the userProfileId and file being sent from the client; (receiving from UserProfileController.java)*/
    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        /*TODO
        *   1. Check if image is not empty
        *   2. Check is file is an image
        *   3. Whether user exists in database
        *   4. If exists, grab some metadata from file if any
        *   5. Store image in S3 and update database with S3 image and link*/
    }
}
