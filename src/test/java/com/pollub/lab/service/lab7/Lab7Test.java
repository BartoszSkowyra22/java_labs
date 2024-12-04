package com.pollub.lab.service.lab7;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class Lab7Test {

    @Test
    void testExercise1_ShouldReturnStaffMap() throws IOException {
        String html = """
            <article>
                <h3>Kierownik Katedry</h3>
                <p><a>dr hab. Tomasz Zientarski</a></p>
                <h3>Profesorowie</h3>
                <p><a>prof. Jan Kowalski</a></p>
                <h3>Doktorzy</h3>
                <p><a>dr Anna Nowak</a><a>dr Piotr Zieliński</a></p>
            </article>
            """;

        Document mockDocument = Jsoup.parse(html);
        Connection mockConnection = mock(Connection.class);

        try (MockedStatic<Jsoup> mockedJsoup = mockStatic(Jsoup.class)) {
            mockedJsoup.when(() -> Jsoup.connect("https://cs.pollub.pl/staff")).thenReturn(mockConnection);
            when(mockConnection.get()).thenReturn(mockDocument);

            Map<String, List<String>> result = Lab7.exercise1();

            assertThat(result).containsKeys("Kierownik Katedry", "Profesorowie", "Doktorzy");
            assertThat(result.get("Kierownik Katedry")).containsExactly("dr hab. Tomasz Zientarski");
            assertThat(result.get("Doktorzy")).containsExactlyInAnyOrder("dr Anna Nowak", "dr Piotr Zieliński");
        }
    }


    @Test
    void testExercise1_ShouldHandleEmptyStaff() throws IOException {
        String html = "<article></article>";
        Document mockDocument = Jsoup.parse(html);
        Connection mockConnection = mock(Connection.class);

        try (MockedStatic<Jsoup> mockedJsoup = mockStatic(Jsoup.class)) {
            mockedJsoup.when(() -> Jsoup.connect("https://cs.pollub.pl/staff")).thenReturn(mockConnection);
            when(mockConnection.get()).thenReturn(mockDocument);

            Map<String, List<String>> result = Lab7.exercise1();

            assertThat(result).isEmpty();
        }
    }


    @Test
    void testExercise2_ShouldPrintDoctorsAndEngineers() {
        Map<String, List<String>> staff = Map.of(
                "Profesorowie", List.of("prof. Jan Kowalski"),
                "Doktorzy", List.of("dr Anna Nowak", "dr inż. Piotr Zieliński")
        );
        Lab7.exercise2(staff);
    }

    @Test
    void testExercise2_ShouldHandleEmptyStaff() {
        Map<String, List<String>> staff = Map.of();
        Lab7.exercise2(staff);
    }

    @Test
    void testExercise3_ShouldReturnNewsSummary() throws IOException {
        String html = """
            <div class="summary">
                <h2>Article 1</h2>
                <p>Description 1</p>
            </div>
            <div class="summary">
                <h2>Article 2</h2>
                <p>Description 2</p>
            </div>
            """;

        Document mockDocument = Jsoup.parse(html);
        Connection mockConnection = mock(Connection.class);

        try (MockedStatic<Jsoup> mockedJsoup = mockStatic(Jsoup.class)) {
            mockedJsoup.when(() -> Jsoup.connect("https://cs.pollub.pl/news/")).thenReturn(mockConnection);
            when(mockConnection.get()).thenReturn(mockDocument);

            Lab7.exercise3();

        }
    }


    @Test
    void testExercise3_ShouldHandleEmptyNews() throws IOException {
        String html = "<div class=\"summary\"></div>";
        Document mockDocument = Jsoup.parse(html);
        Connection mockConnection = mock(Connection.class);

        try (MockedStatic<Jsoup> mockedJsoup = mockStatic(Jsoup.class)) {
            mockedJsoup.when(() -> Jsoup.connect("https://cs.pollub.pl/news/")).thenReturn(mockConnection);
            when(mockConnection.get()).thenReturn(mockDocument);

            Lab7.exercise3();
        }
    }


    @Test
    void testCompare_ShouldIgnoreTitles() {
        NameComparator comparator = new NameComparator();
        int result = comparator.compare("dr Anna Nowak", "prof. Jan Kowalski");
        assertThat(result).isLessThan(0);
    }

    @Test
    void testCompare_ShouldHandleEqualNames() {
        NameComparator comparator = new NameComparator();
        int result = comparator.compare("dr Anna Nowak", "mgr Anna Nowak");
        assertThat(result).isEqualTo(0);
    }
}
