import com.okta.sdk.client.AuthorizationMode;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;

import java.util.Arrays;
import java.util.HashSet;

public class Okta {
    public static void main(String[] args) {
        Client client = Clients.builder()
                .setOrgUrl("https://dev-43293894.okta.com")
                .setAuthorizationMode(AuthorizationMode.PRIVATE_KEY)
                .setClientId("{clientId}")
                .setKid("{kid}") // key id (optional)
                .setScopes(new HashSet<>(Arrays.asList("okta.users.read", "okta.apps.read")))
                .setPrivateKey("/path/to/yourPrivateKey.pem")
                // (or) .setPrivateKey("full PEM payload")
                // (or) .setPrivateKey(Paths.get("/path/to/yourPrivateKey.pem"))
                // (or) .setPrivateKey(inputStream)
                // (or) .setPrivateKey(privateKey)
                .build();
    }
}
