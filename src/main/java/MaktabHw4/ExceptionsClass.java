package MaktabHw4;

import Exceptions.*;

public class ExceptionsClass {
    public void NotInsertException () {
        throw new NotInserException("Failed to insert the information ");
    }
    public void failedToReseved () {
        throw new FaildeToReservedException("Failed to reserve the ticket ");
    }
    public void failedToSearchMovieByNameException () {
        throw new FailedToSearchMovieByNameException("Failed to search movie by it name! ");
    }
    public void failedToAddTicketException () {
        throw new FailedToAddTicketException("Failed to add the ticket! ");
    }
    public void failedToDeleteTicketException () {
        throw new FailedToAddTicketException("Failed to delete the ticket! ");
    }
    public void failedToRegisterCinemaException () {
        throw new FailedToRegisterTheCinemaException("Failed to delete the ticket! ");
    }
    public void LoginProblemException () {
        throw new LoginProblemException("Failed to Login! ");
    }
}
