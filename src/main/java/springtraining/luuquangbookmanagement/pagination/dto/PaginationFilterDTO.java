package springtraining.luuquangbookmanagement.pagination.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationFilterDTO {

    private String search;

    private int page;

    private int limit;

    private String orderBy;

}
