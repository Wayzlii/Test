package otus.pages;

import com.microsoft.playwright.Page;
import otus.annotation.Path;
import otus.common.AbsCommon;

public abstract class AbsBasePage<T> extends AbsCommon {

    private final String baseUrl;

    protected AbsBasePage(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private String getPath() {
        Class<T> clazz = (Class<T>)getClass();
        if(clazz.isAnnotationPresent(Path.class)) {
            Path path = clazz.getDeclaredAnnotation(Path.class);
            return path.value();
        }
        return "";
    }

    public T open(Page page) {
        page.navigate(this.baseUrl + getPath());
        return (T) this;
    }

}
