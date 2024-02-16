package guru.springframework.sfgdi.services;

public class ScopeService {

    private final String scope;

    public ScopeService(String scope) {
        this.scope = scope;
        System.out.println("Creating a " + scope + " bean!!!!!!!!!!!!!!!!!!!!");
    }

    public String getScope() {
        return "I'm a " + scope + " bean.";
    }
}
