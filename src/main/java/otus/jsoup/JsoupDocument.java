package otus.jsoup;

import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import otus.pages.CoursePage;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static otus.util.DateUtil.getDate;

@Getter
public class JsoupDocument {

    private final LocalDate minDate;

    private final LocalDate maxDate;

    private final List<Card> cardsMin;
    private final List<Card> cardsMax;

    public JsoupDocument(CoursePage coursePage) {
        Document document = Jsoup.parse(coursePage.getPageSource());
        Elements elementCards = document.selectXpath(
                "//section/descendant::a[contains(@href,'/lessons/')]"
        );
        List<LocalDate> dates = getDates(elementCards);
        minDate = dates.get(0);
        maxDate = dates.get(dates.size() - 1);
        cardsMin = getCards(elementCards, minDate);
        cardsMax = getCards(elementCards, maxDate);
    }

    private List<LocalDate> getDates(Elements elementCards) {
        return elementCards.stream()
                .map(card -> card.selectXpath(
                        "descendant::div[contains(text(), 'месяц')]/text()",
                        TextNode.class
                ))
                .flatMap(textNodes -> textNodes.stream())
                .map(textNode -> textNode.text().replace(",", ""))
                .map(string -> getDate(string))
                .filter(localDate -> localDate != null)
                .sorted()
                .toList();
    }

    private List<Card> getCards(Elements cardElements, LocalDate filterDate) {
        return cardElements.stream()
                .map(card -> {
                            List<TextNode> dateNodes = card.selectXpath(
                                    "descendant::div[contains(text(), 'месяц')]/text()",
                                    TextNode.class
                            );
                            if (dateNodes.isEmpty())
                                return null;
                            LocalDate courseDate = getDate(
                                    dateNodes.get(0).text().replace(",", ""));
                            if (courseDate == null) {
                                return null;
                                //одна из карточек курса имеет не полную дату (прописан только месяц и год)
                            }
                            String courseName = card.selectXpath(
                                    "descendant::h6/div/text()",
                                    TextNode.class
                            ).get(0).text().trim();
                            String hrefCard = card.attributes().get("href");
                            return new Card(courseDate, courseName, hrefCard);
                        }
                )
                .filter(Objects::nonNull)
                .filter(card -> card.dateStart().equals(filterDate))
                .toList();
    }
}

