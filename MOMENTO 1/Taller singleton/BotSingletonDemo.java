// Case study: The Single Support Bot
// Singleton pattern in Java

class BotConfigManager {

    private static BotConfigManager instance;

    private String apiToken;
    private String language;
    private String prefix;

    private BotConfigManager() {
        System.out.println("Loading bot configuration...");
        this.apiToken = "TG-8f92-SECRET";
        this.language = "en";
        this.prefix = "!";
    }

    public static synchronized BotConfigManager getInstance() {
        if (instance == null) {
            instance = new BotConfigManager();
        }
        return instance;
    }

    public String getApiToken() {
        return apiToken;
    }

    public String getLanguage() {
        return language;
    }

    public String getPrefix() {
        return prefix;
    }

    public void changePrefix(String newPrefix) {
        this.prefix = newPrefix;
    }
}

public class BotSingletonDemo {
    public static void main(String[] args) {

        // Three different users type commands to the bot
        BotConfigManager user1 = BotConfigManager.getInstance();
        System.out.println("Camila types: " + user1.getPrefix() + "help");

        BotConfigManager user2 = BotConfigManager.getInstance();
        System.out.println("Julian types: " + user2.getPrefix() + "ping");

        BotConfigManager user3 = BotConfigManager.getInstance();
        System.out.println("Andrea types: " + user3.getPrefix() + "info");

        // Check that all three are actually the same object
        System.out.println("\nuser1 == user2: " + (user1 == user2));
        System.out.println("user2 == user3: " + (user2 == user3));

        // An admin changes the prefix and it affects everyone, since it's one single object
        System.out.println("\nAn admin changes the prefix to '/'");
        user1.changePrefix("/");

        System.out.println("Andrea types again: " + user3.getPrefix() + "rules");
    }
}
