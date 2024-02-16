package guru.springframework.sfgdi;

import guru.springframework.sfgdi.config.datasource.FakeConstructorDataSource;
import guru.springframework.sfgdi.config.datasource.FakeDataSource;
import guru.springframework.sfgdi.controllers.*;
import guru.springframework.sfgdi.services.ScopeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
//@ComponentScan("guru.springframework")
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);



		PetController petController = ctx.getBean("petController", PetController.class);
		System.out.println("--- The Best Pet is ---");
		System.out.println(petController.whichPetIsTheBest());

		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println(i18nController.sayHello());

		MyController myController = (MyController) ctx.getBean("myController");

		System.out.println("------- Primary Bean");
		System.out.println(myController.sayHello());

		System.out.println("------ Property");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("--------- Setter");
		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("-------- Constructor" );
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());

		System.out.println("-------- Scopes");
		ScopeService singletonBean1 = (ScopeService) ctx.getBean("singletonBean");
		System.out.println(singletonBean1.getScope());
		ScopeService singletonBean2 = (ScopeService) ctx.getBean("singletonBean");
		System.out.println(singletonBean2.getScope());

		ScopeService prototypeBean1 = (ScopeService) ctx.getBean("prototypeBean");
		System.out.println(prototypeBean1.getScope());
		ScopeService prototypeBean2 = (ScopeService) ctx.getBean("prototypeBean");
		System.out.println(prototypeBean2.getScope());

		ExecutorService ex = Executors.newFixedThreadPool(2);
		ex.submit(() -> {
			ScopeService singletonBean3 = (ScopeService) ctx.getBean("singletonBean");
			System.out.println(singletonBean3.getScope());
		});
		ex.submit(() -> {
			ScopeService singletonBean3 = (ScopeService) ctx.getBean("singletonBean");
			System.out.println(singletonBean3.getScope());
		});
		ex.submit(() -> {
			ScopeService singletonBean3 = (ScopeService) ctx.getBean("singletonBean");
			System.out.println(singletonBean3.getScope());
		});
		ex.submit(() -> {
			ScopeService singletonBean3 = (ScopeService) ctx.getBean("singletonBean");
			System.out.println(singletonBean3.getScope());
		});
		ex.shutdown();

		System.out.println("------------ Properties");
		FakeDataSource fds = ctx.getBean(FakeDataSource.class);
		System.out.println("User: " + fds.getUsername());
		System.out.println("Pass:" + fds.getPassword());
		System.out.println("Url: " + fds.getUrl());

		System.out.println("------------ Constructor binding Properties");
		FakeConstructorDataSource fcds = ctx.getBean(FakeConstructorDataSource.class);
		System.out.println("User: " + fcds.getUsername());
		System.out.println("Pass:" + fcds.getPassword());
		System.out.println("Url: " + fcds.getUrl());
	}

}
