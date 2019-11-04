package by.sam.mvc.exceptions;


import by.sam.mvc.exceptions.dao.menu.DefaultDishDaoException;
import by.sam.mvc.exceptions.dao.menu.DefaultMenuDaoException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//TODO:
//to handle each exception
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DefaultMenuDaoException.class)
    public String handleDefaultMenuDaoException(){
        return "error";
    }
    @ExceptionHandler(DefaultDishDaoException.class)
    public String handleDefaultDishDaoException(){
        return "error";
    }
}
