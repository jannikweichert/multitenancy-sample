package de.weichert.demo.multitenancy;

import de.weichert.multitenancy.EnableMultiTenancy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Jannik on 02.07.15.
 */
@SpringBootApplication
@EnableMultiTenancy
public class Application {

    private static ConfigurableApplicationContext CONTEXT;

    public static void main(String[] args){
        CONTEXT = new SpringApplication(Application.class).run(args);
    }

    public static ConfigurableApplicationContext getContext(){
        return CONTEXT;
    }
}
