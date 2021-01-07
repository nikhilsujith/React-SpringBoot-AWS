package tech.nikhilsujith.reactspringaws.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
// React throws Cross origin error when accessing server on a different host.
//This line "@CrossOrigin" says accept from anywhere
@CrossOrigin("*")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
//    method to return a list of users
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }


    /*TODO: Take the following from the client and pass it to "userProfileService"
     *   1. @PathVariable : Path Variable for the user profile ID: Used to assign image to a particular user
     *   2. @RequestParam: The actual image which the user intends to upload*/
    /*Image upload API*/
    @PostMapping(
            path = "{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId,
                                       @RequestParam("file")MultipartFile file) {
        userProfileService.uploadUserProfileImage(userProfileId, file);
    }

    @GetMapping("{userProfileId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId){
        return userProfileService.downloadUserProfileImage(userProfileId);
    }
}
