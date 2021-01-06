package tech.nikhilsujith.reactspringaws.datastore;

import org.springframework.stereotype.Repository;
import tech.nikhilsujith.reactspringaws.profile.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ProxyTestUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "JohnnyDoe", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "JanneDoe", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "TimmyDoo", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Dofentsomething", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Yabadabadoo", null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
