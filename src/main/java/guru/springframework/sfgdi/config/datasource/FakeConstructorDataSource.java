package guru.springframework.sfgdi.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

@ConstructorBinding
@ConfigurationProperties("guru")
public class FakeConstructorDataSource {

    public final String username;
    public final String password;
    public final String url;

    public FakeConstructorDataSource(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }


}
