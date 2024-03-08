//Логика кода:
// 1.Создание класса PhoneBook, который хранит телефонную книгу в виде Map, где ключом является имя абонента, а значением список его телефонных номеров.
// 2.В конструкторе PhoneBook инициализируется пустая телефонная книга в виде HashMap.
// 3.Метод add(name, phoneNum) добавляет телефонный номер phoneNum для абонента с именем name. Если абонент с таким именем уже существует, то номер добавляется в его список номеров, иначе создается новая запись в книге.
// 4.Метод find(name) возвращает список телефонных номеров для абонента с именем name, либо пустой список, если такого абонента нет.
// 5.Метод getPhoneBook() возвращает отсортированную по количеству номеров телефонную книгу в виде Map, где ключами являются имена абонентов, а значениями списки их номеров. Используется Stream API для сортировки и преобразования.
// 6.В методе main создается экземпляр PhoneBook, добавляются телефонные номера для абонентов "Alice" и "Bob", а также повторно добавляется номер для "Alice".
// 7.Выводятся номера телефона для абонента "Alice" с помощью метода find().
// 8.Выводятся все записи в телефонной книге с помощью метода getPhoneBook(), итерируя по каждой записи и выводя имя абонента и список его номеров.


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class PhoneBook {
    private Map<String, List<Integer>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String name, Integer phoneNum) {
        if (phoneBook.containsKey(name)) {
            phoneBook.get(name).add(phoneNum);
        } else {
            List<Integer> phoneNumberList = new ArrayList<>();
            phoneNumberList.add(phoneNum);
            phoneBook.put(name, phoneNumberList);
        }
    }

    public List<Integer> find(String name) {
        return phoneBook.getOrDefault(name, new ArrayList<>());
    }

    public Map<String, List<Integer>> getPhoneBook() {
        return phoneBook.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Alice", 123456789);
        phoneBook.add("Bob", 987654321);
        phoneBook.add("Alice", 999999999);
        
        System.out.println("Phone numbers for Alice: " + phoneBook.find("Alice"));
        
        System.out.println("Phone book entries:");
        Map<String, List<Integer>> phoneBookMap = phoneBook.getPhoneBook();
        phoneBookMap.forEach((name, numbers) -> System.out.println(name + ": " + numbers));
    }
}