package com.embed.project;

import org.springframework.ai.vectorstore.oracle.autoconfigure.OracleVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = OracleVectorStoreAutoConfiguration.class)
public class EmbedProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmbedProjectApplication.class, args);
	}

}
