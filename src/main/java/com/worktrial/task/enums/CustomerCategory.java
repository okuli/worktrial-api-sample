package com.worktrial.task.enums;

import com.worktrial.task.exceptions.BaseException;
import com.worktrial.task.exceptions.CustomerException;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CustomerCategory {
    NEW_CUSTOMER,
    EXISTING_CUSTOMER,
    INACTIVE_CUSTOMER,
    ;
    private static Map<String, CustomerCategory> map = Stream.of(values())
            .collect(Collectors.toMap(Enum::name, Function.identity() )) ;

    public static CustomerCategory fromString(String category) throws BaseException {
        if (!map.containsKey(category)) {
            throw new CustomerException(HttpStatus.BAD_REQUEST, "Category " + category + " is not available!");
        }
        return map.get(category);
    }
}
