import java.util.LinkedList;
import java.util.Scanner;

class User {
    String name;
    LinkedList<User> friends;
    LinkedList<String> posts;
    
    User(String name) {
        this.name = name;
        this.friends = new LinkedList<>();
        this.posts = new LinkedList<>();
    }
}

public class SocialMediaApp {
    LinkedList<User> users = new LinkedList<>();

    // O(n)
    void addUser(String name) {
        users.add(new User(name));
    }

    // O(n)
    void addFriend(String name1, String name2) {
        User user1 = findUser(name1);
        User user2 = findUser(name2);

        if (user1 != null && user2 != null) {
            user1.friends.add(user2);
            user2.friends.add(user1);
        }
    }

    // O(n)
    void addPost(String name, String post) {
        User user = findUser(name);
        if (user != null) {
            user.posts.add(post);
        }
    }

    // O(n)
    void showPosts(String name) {
        User user = findUser(name);
        if (user != null) {
            for (String post : user.posts) {
                System.out.println(user.name + ": " + post);
            }
        }
    }

    // O(n)
    void showFriends(String name) {
        User user = findUser(name);
        if (user != null) {
            for (User friend : user.friends) {
                System.out.println(friend.name);
            }
        }
    }

    // O(n^2)
    void showMutuals(String name1, String name2) {
        User user1 = findUser(name1);
        User user2 = findUser(name2);

        if (user1 != null && user2 != null) {
            for (User friend1 : user1.friends) {
                for (User friend2 : user2.friends) {
                    if (friend1.name.equals(friend2.name)) {
                        System.out.println(friend1.name);
                    }
                }
            }
        }
    }

    // O(n)
    void removeFriend(String name1, String name2) {
        User user1 = findUser(name1);
        User user2 = findUser(name2);

        if (user1 != null && user2 != null) {
            user1.friends.removeIf(friend -> friend.name.equals(name2));
            user2.friends.removeIf(friend -> friend.name.equals(name1));
        }
    }

    // Helper method to find a user
    User findUser(String name) {
        for (User user : users) {
            if (user.name.equals(name)) {
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SocialMediaApp app = new SocialMediaApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add user\n2. Add friend\n3. Add post\n4. Show posts\n5. Show friends\n6. Show mutual\n7. Remove friend\n8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the user: ");
                    String name = scanner.nextLine();
                    app.addUser(name);
                    break;
                case 2:
                    System.out.print("Enter the name of user 1: ");
                    String name1 = scanner.nextLine();
                    System.out.print("Enter the name of user 2: ");
                    String name2 = scanner.nextLine();
                    app.addFriend(name1, name2);
                    break;
                case 3:
                    System.out.print("Enter the name of the user: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter the post: ");
                    String post = scanner.nextLine();
                    app.addPost(userName, post);
                    break;
                case 4:
                    System.out.print("Enter the name of the user: ");
                    String nameForPosts = scanner.nextLine();
                    app.showPosts(nameForPosts);
                    break;
                case 5:
                    System.out.print("Enter the name of the user: ");
                    String nameForFriends = scanner.nextLine();
                    app.showFriends(nameForFriends);
                    break;
                case 6:
                    System.out.print("Enter the name of user 1: ");
                    String mutualName1 = scanner.nextLine();
                    System.out.print("Enter the name of user 2: ");
                    String mutualName2 = scanner.nextLine();
                    app.showMutuals(mutualName1, mutualName2);
                    break;
                case 7:
                    System.out.print("Enter the name of user 1: ");
                    String remName1 = scanner.nextLine();
                    System.out.print("Enter the name of user 2: ");
                    String remName2 = scanner.nextLine();
                    app.removeFriend(remName1, remName2);
                    break;
                case 8:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
