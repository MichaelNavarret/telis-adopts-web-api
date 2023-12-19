package com.api.telisadoptproyect.library.util;

import com.api.telisadoptproyect.library.exception.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;

import java.util.Locale;

public class PaginationUtils {
    public static final String X_PAGINATION_NUM = "X-PAGINATION-NUM";
    public static final String X_PAGINATION_LIMIT = "X-PAGINATION-LIMIT";

    public static final String X_SORTING_FIELD = "X-SORTING-FIELD";
    public static final String X_SORTING_DIRECTION = "X-SORTING-DIRECTION";
    public static final String X_PAGINATION_TOTAL_RECORDS = "X-PAGINATION-TOTAL-RECORDS";
    public static final String X_PAGINATION_TOTAL_PAGES = "X-PAGINATION-TOTAL-PAGES";
    public static final String X_PAGINATION_CURRENT_PAGE_NUM = "X-PAGINATION-CURRENT-PAGE-NUM";
    public static final String X_PAGINATION_HAS_NEXT_PAGE = "X-PAGINATION-HAS-NEXT-PAGE";
    public static final String X_PAGINATION_NEXT_PAGE_NUM = "X-PAGINATION-NEXT-PAGE-NUM";
    public static final Integer NO_MORE_PAGES = -1;
    public static final String DEFAULT_PAGINATION_LIMIT = "20";
    public static final String DEFAULT_PAGINATION_NUM = "0";

    public static <T> HttpHeaders createHttpHeaderForPagination(Page<T> page, Integer pageLimitValue) {
        return createHttpHeaderForPagination(page.getTotalElements(), page.getNumber(), pageLimitValue, page.getNumberOfElements());
    }

    public static HttpHeaders createHttpHeaderForPagination(Long totalCount, Integer pageNum,
                                                            Integer pageLimit, Integer resultSize) {

        final HttpHeaders headerInfo = new HttpHeaders();

        final Long results = totalCount - (pageNum.longValue() + 1) * pageLimit.longValue();

        headerInfo.add("Access-Control-Expose-Headers", "*");
        headerInfo.add(X_PAGINATION_LIMIT, String.valueOf(resultSize));
        headerInfo.add(X_PAGINATION_TOTAL_RECORDS, String.valueOf(totalCount));
        headerInfo.add(X_PAGINATION_TOTAL_PAGES,
                String.valueOf((int) Math.ceil((double) totalCount / (double) pageLimit)));
        headerInfo.add(X_PAGINATION_CURRENT_PAGE_NUM, String.valueOf(pageNum));
        if (results > 0 && resultSize.equals(pageLimit)) {
            headerInfo.add(X_PAGINATION_NEXT_PAGE_NUM, String.valueOf(pageNum + 1));
            headerInfo.add(X_PAGINATION_HAS_NEXT_PAGE, String.valueOf(true));
        } else {
            headerInfo.add(X_PAGINATION_NEXT_PAGE_NUM, String.valueOf(NO_MORE_PAGES));
            headerInfo.add(X_PAGINATION_HAS_NEXT_PAGE, String.valueOf(false));

        }
        return headerInfo;
    }

    public static <T> HttpHeaders createHttpSortingHeaderForPagination(String sort) {
        if (sort == null || sort.isEmpty()) return null;
        final HttpHeaders headerInfo = new HttpHeaders();

        String[] sortValues = sort.split(":");
        if (sortValues.length != 2) throw new BadRequestException("invalid_sort_option");

        headerInfo.add(X_SORTING_FIELD, sortColumnValueFromString(sortValues[0]));
        headerInfo.add(X_SORTING_DIRECTION, sortDirectionFromString(sortValues[1]).name().toUpperCase(Locale.ROOT));
        return headerInfo;
    }

    public static Sort createSortCriteria(@NonNull String sortFields) throws BadRequestException {
        return createSortCriteria(sortFields, null);
    }

    public static Sort createSortCriteria(String sortFields, String defaultSort) throws BadRequestException {
        String sortDescriptor = sortFields == null || sortFields.isEmpty() ? defaultSort : sortFields ;

        String sortColumnName = "id";
        Sort.Direction sortDirection = Sort.Direction.DESC;

        if (sortDescriptor != null && sortDescriptor.isEmpty() == false) {
            if (sortDescriptor.contains(":")) {
                String[] sortSpecs = sortDescriptor.split(":");
                if (sortSpecs.length != 2) {
                    throw new BadRequestException("invalid_sort_option");
                }

                sortColumnName = sortSpecs[0];
                sortDirection = sortDirectionFromString(sortSpecs[1]);
            } else {
                sortColumnName = sortDescriptor;
            }
        }
        return Sort.by(sortDirection, sortColumnName);
    }

    public static Sort.Direction sortDirectionFromString(String sortDirStr) throws BadRequestException {
        if ("asc".equalsIgnoreCase(sortDirStr)) return Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDirStr)) return Sort.Direction.DESC;
        throw new BadRequestException("invalid_sort_direction");
    }

    public static String sortColumnValueFromString(String columnValue){
        if (columnValue == null || columnValue.isEmpty()) throw new BadRequestException("invalid_sort_column_value");
        return columnValue;
    }

}
