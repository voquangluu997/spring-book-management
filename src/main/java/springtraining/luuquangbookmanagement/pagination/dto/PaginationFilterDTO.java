package springtraining.luuquangbookmanagement.pagination.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class PaginationFilterDTO {
    private String search;

    private int page;

    private int limit;

    private String orderBy;
}
