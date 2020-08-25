package com.joaooliveira.bookstoremanager.mapper;

import com.joaooliveira.bookstoremanager.dto.BookDTO;
import com.joaooliveira.bookstoremanager.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper/*anotação que faz em tempo de execução a instanciação do tipo desta classe
e faz a transferência dos dados*/
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class); /*fazer a instanciação do mapper dentro da interface, para que está interface possa ser criada através de uma constante*/

    Book toModel(BookDTO bookDTO); /*conversão do tipo DTO para Book*/

    BookDTO toDTO(Book book); /*conversão do tipo Book para DTO*/
}
