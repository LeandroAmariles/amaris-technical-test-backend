package com.amaris.technicaltest.factory;


import com.amaris.technicaltest.controller.out.EmployeeResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@Getter
@RequiredArgsConstructor
public enum ResponseFactory {

    RESPONSE_NORMAL(() -> new EmployeeResponse(
            1L,"imageTest", "Test","testLast","email@Test",1.00
            ,"12345",30,"dobTest", 1235.00,"av-siempre-viva-123"
    ));

    private final Supplier<EmployeeResponse> employeeResponse;


}
