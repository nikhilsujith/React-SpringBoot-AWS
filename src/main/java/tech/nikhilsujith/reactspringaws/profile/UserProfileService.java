package tech.nikhilsujith.reactspringaws.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
