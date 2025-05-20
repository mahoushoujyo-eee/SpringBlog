package org.eee.account;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class AccountApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(AccountApplication.class, args);
    }
}
