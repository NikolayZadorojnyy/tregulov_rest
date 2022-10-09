package org.example.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler { // класс, который р=отвечает за обработку исключений

    @ExceptionHandler // метод, ответственный за обработку исключений
    public ResponseEntity<EmployeeIncorrectData> handleException (NoSuchEmployeeException exception) {  // вводим в url id которого нет в базе
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler // метод, ответственный за обработку исключений
    public ResponseEntity<EmployeeIncorrectData> handleException1 (Exception exception) { // вводим в url вместо id невалидное значение, abc например
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }


}
