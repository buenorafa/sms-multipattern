package api.translation.decorator;

public interface TranslationService {
    String translate(String fromLang, String toLang, String text) throws Exception;
}
