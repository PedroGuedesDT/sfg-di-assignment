package guru.springframework.sfgdi.config;

import guru.springframework.pets.CatPetService;
import guru.springframework.pets.DogPetService;
import guru.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.config.datasource.FakeConstructorDataSource;
import guru.springframework.sfgdi.services.I18NSpanishService;
import guru.springframework.sfgdi.services.I18nEnglishGreetingService;
import guru.springframework.sfgdi.services.ScopeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EnableConfigurationProperties(FakeConstructorDataSource.class)
@ImportResource("classpath:sfgdi-config.xml")
@Configuration
public class ServicesConfiguration {

    @Bean
    String profilesDebugger(@Value("${spring.profiles}") List<String> profiles) {
        String debugMessage = "Active Pet Profile Types: " + String.join(", ", profiles);
        System.out.println(debugMessage);
        return debugMessage;
    }

    @Bean
    PetServiceFactory petServiceFactory() {
        return new PetServiceFactory();
    }

    @Profile({"cat"})
    @Bean("petService")
    CatPetService catPetService() {
        return (CatPetService) petServiceFactory().getPetService("cat");
    }

    @Profile({"dog", "default"})
    @Bean("petService")
    DogPetService dogPetService(ApplicationContext applicationContext) {
        return (DogPetService) petServiceFactory().getPetService("dog");
    }

    @Profile("EN")
    @Bean("i18nService")
    I18nEnglishGreetingService i18nEnglishGreetingService() {
        return new I18nEnglishGreetingService();
    }

    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18NSpanishService i18NSpanishService() {
        return new I18NSpanishService();
    }

    @Bean
    ScopeService singletonBean() {
        return new ScopeService(ConfigurableBeanFactory.SCOPE_SINGLETON);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    ScopeService prototypeBean() {
        return new ScopeService(ConfigurableBeanFactory.SCOPE_PROTOTYPE);
    }

}
