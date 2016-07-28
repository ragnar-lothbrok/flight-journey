package com.ixigo.flights.validators;

public interface IValidatable<T> {

	Boolean validate(IValidator<T> validator);
}
