package tech.nikhilsujith.reactspringaws.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tech.nikhilsujith.reactspringaws.datastore.ProxyTestUserProfileDataStore;

import java.util.List;

@Repository
public class UserProfileServiceDataAccessService {

//    Change this line of code to switch to a real database
    private final ProxyTestUserProfileDataStore proxyTestUserProfileDataStore;

    @Autowired //Dependency injection
    public UserProfileServiceDataAccessService(ProxyTestUserProfileDataStore proxyTestUserProfileDataStore) {
        this.proxyTestUserProfileDataStore = proxyTestUserProfileDataStore;
    }

    List<UserProfile> getUserProfiles() {
        return proxyTestUserProfileDataStore.getUserProfiles();
    }
}
