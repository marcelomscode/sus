package fiap.sus.config;

public class DotenvInitializer {
    static {
        io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.configure()
                .directory(".") // raiz do projeto
                .ignoreIfMissing() // ignora se o .env não estiver presente
                .load();

        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue()));
    }
}