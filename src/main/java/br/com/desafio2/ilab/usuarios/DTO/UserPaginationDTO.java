package br.com.desafio2.ilab.usuarios.DTO;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.desafio2.ilab.usuarios.models.User;
import lombok.Data;

@Data
public class UserPaginationDTO {
    private int totalPages;
    private int currentPage;
    private int totalElements;
    private int limit;
    @JsonProperty("isFirstPage")
    private boolean isFirstPage;
    @JsonProperty("isLastPage")
    private boolean isLastPage;
    @JsonProperty("isEmpty")
    private boolean isEmpty;
    private List<User> content;

    public UserPaginationDTO(Page<User> pagination) {
        totalPages = pagination.getTotalPages();
        currentPage = pagination.getNumber();
        totalElements = pagination.getNumberOfElements();
        limit = pagination.getSize();
        isFirstPage = pagination.isFirst();
        isLastPage = pagination.isLast();
        isEmpty = pagination.isEmpty();
        content = pagination.getContent();
    }

}
