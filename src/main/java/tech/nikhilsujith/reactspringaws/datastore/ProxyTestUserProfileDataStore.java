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
        USER_PROFILES.add(new UserProfile(UUID.fromString("368e3865-6288-411a-aedf-d12bc2ff52d9"), "JohnnyDoe", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("a307eb37-6556-4163-b5fb-f826d60f930b"), "JanneDoe", null));
//        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "JohnnyDoe", null));
//        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "JanneDoe", null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
