package com.joaooliveira.bookstoremanager.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.joaooliveira.bookstoremanager.dto.AuthorDTO;
import com.joaooliveira.bookstoremanager.dto.BookDTO;
import com.joaooliveira.bookstoremanager.entity.Book;

import static com.joaooliveira.bookstoremanager.utils.AuthorUtils.createFakeAuthor;
import static com.joaooliveira.bookstoremanager.utils.AuthorUtils.createFakeAuthorDTO;

/*classe usada para criação de objetos fakes para utilização nos testes*/
public class BookUtils {
    private static final Faker faker = new Faker(); /*biblioteca Faker - gera dados aleatórios de acordo com as condições impostas*/

    public static BookDTO createFakeBookDTO() {
        return BookDTO.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 20))
                .isbn("0-49652068-9")
                .publisherName(faker.book().publisher())
                .author(createFakeAuthorDTO())
                .build();
    }


    public static Book createFakeBook() {
        return Book.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 20))
                .isbn("0-49652068-9")
                .publisherName(faker.book().publisher())
                .author(createFakeAuthor())
                .build();
    }

    public static String asJsonString(BookDTO bookDTO) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, true);
            objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(bookDTO);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
