package com.company;

public class Main {
    public static void main(String[] args) {
        Course course = new Course(
                new Cross(300),
                new Wall(30),
                new Water(5)
        );

        Team team = new Team(
                new Human("Петя"),
                new Cat("Барсик"),
                new Dog("Филя"),
                new Dog("Чарлик")
        );

        course.doIt(team);
        team.showResults();
    }
}
