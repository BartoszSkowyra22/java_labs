package com.pollub.lab.service.lab8;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.mockito.Mockito.*;

class ValidationRequestServiceTest {

    private final BindingResult bindingResult = mock(BindingResult.class);
    private final ValidationRequestService validationRequestService = new ValidationRequestService();

    @Test
    void testValidateRequest_ShouldThrowExceptionWhenErrorsExist() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new org.springframework.validation.FieldError("object", "field", "error message")
        ));

        Exception exception = catchException(() -> validationRequestService.validateRequest(bindingResult));

        assertThat(exception).isInstanceOf(Exception.class).hasMessageContaining("field: error message");
    }
}
