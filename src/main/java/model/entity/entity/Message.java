package model.entity.entity;

public enum Message {
    USER_WITH_CREDENTIALS_NOT_EXISTS("no user found with this password and name, %s %s"),
    NAME_ENGAGED("name is used by another user, %s"),
    CAR_NUMBER_ENGAGED("car number is used by another car, %s"),
    CAR_NUMBER_NOT_MATCHES_LENGTH("car number should have length between 1 and 8, %s"),
    NAME_NOT_MATCHES_LENGTH("name should have length between 6 and 30, %s"),
    PASSWORD_NOT_MATCHES_LENGTH("password should have length between 6 and 30, %s"),
    CAR_IS_PARKED("first you have to pick up the car from parking place, %s"),
    PARKING_PLACE_ENGAGED("parking place is engaged by another car, %s"),
    WRONG_PARKING_PLACE_ID("wrong parking place id, %d"),
    CAR_NOT_BELONGS_TO_YOU("car doesn't belong to you, %s"),
    CAR_NOT_PARKED("car isn't parked, %s");
    private final String message;
    Message(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
