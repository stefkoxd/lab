package mk.ukim.finki.lab.exceptions;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException(){
        super("Invalid arguments provided!");
    }
}
