package de.weichert.demo.multitenancy.configuration;

import bitronix.tm.TransactionManagerServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jannik on 05.08.15.
 */
@Configuration
public class BitronixConfiguration {

    @Bean
    public bitronix.tm.Configuration btmConfig() {
        return TransactionManagerServices.getConfiguration()
                .setDisableJmx(true)
                .setServerId("spring-btm_" + System.currentTimeMillis())
                .setLogPart1Filename("./tx-logs/part1_" + System.currentTimeMillis() + ".btm")
                .setLogPart2Filename("./tx-logs/part2_" + System.currentTimeMillis() + ".btm");
    }
}
