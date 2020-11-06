package ru.platonov.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.platonov.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNT;

    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "Cer"));
        people.add(new Person(++PEOPLE_COUNT, "Wer"));
        people.add(new Person(++PEOPLE_COUNT, "qwq"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        //использование стрим фильтр  ищет персону с айди которое указали в качестве аргумента
        //findAny() возвращает это значение а orElse возвращает нулл если такого айди нет
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    //создает нового person (пока просто добавляет в список)
    public void save(Person person) {
        person.setId(++PEOPLE_COUNT); //по идеи при создании в бд она сама генерируется
        people.add(person);
    }

    //метод для обновления персонажа по id
    public void update(int id, Person updetesPerson) {
        Person personToBeUpdated = show(id);
        //обновление имени person
        personToBeUpdated.setName(updetesPerson.getName());
    }

    //для удаления песонажа по id
    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
