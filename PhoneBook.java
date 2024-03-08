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