package com.ultimatesystem.school.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .addOpenApiCustomiser(openApi -> {
                    Contact contact = new Contact();
                    contact.setName("Blazej Bujala");
                    contact.setEmail("bb@gmail.com");
                    Info info = new Info();
                    info.setTitle("School");
                    info.setDescription("Teacher-Students system");
                    info.setContact(contact);
                    info.setVersion("0.0.1");
                    openApi.setInfo(info);
                })
                .group("school-system-blazej-bujala")
                .pathsToMatch("/**")
                .build();
    }
}
