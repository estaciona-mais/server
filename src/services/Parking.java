package server.services;

import models.User;

public class Parking {
    private User[] users;
    private Vacancy[] vacancies;
    private int totalUsers; // possibly used to prevent mass creation of users
    private int totalVacancies;
}
