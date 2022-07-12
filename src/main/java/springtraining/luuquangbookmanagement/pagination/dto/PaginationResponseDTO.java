package springtraining.luuquangbookmanagement.pagination.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class PaginationResponseDTO {
    private int currentPage;

    private long totalItems;

    private int totalPages;
}
