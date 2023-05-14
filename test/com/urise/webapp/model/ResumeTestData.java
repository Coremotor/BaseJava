package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static EnumMap<ContactsType, String> getContacts() {
        return new EnumMap<>(ContactsType.class) {{
            put(ContactsType.PHONE, "+7(921) 855-0482");
            put(ContactsType.SKYPE, "skype:grigory.kislin");
            put(ContactsType.EMAIL, "gkislin@yandex.ru");
            put(ContactsType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
            put(ContactsType.GITHUB, "https://github.com/gkislin");
            put(ContactsType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
            put(ContactsType.HOME_PAGE, "https://gkislin.ru/");
        }};
    }

    public static Resume getResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
//        Map<SectionsType, AbstractSection> sections = new EnumMap<>(SectionsType.class);
//        List<String> textAchievements = new ArrayList<>() {{
//            add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения " +
//                    "автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на " +
//                    "Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для " +
//                    "комплексных DIY смет");
//            add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный" +
//                    " maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие " +
//                    "(JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
//            add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция " +
//                    "с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
//            add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, " +
//                    "Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
//                    "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
//                    "интеграция CIFS/SMB java сервера.");
//            add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, " +
//                    "GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
//            add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base " +
//                    "архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через" +
//                    " систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга " +
//                    "системы по JMX (Python/ Django).");
//            add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, " +
//                    "Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
//        }};
//        List<String> textQualifications = new ArrayList<>() {{
//            add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
//            add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
//            add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, " +
//                    "HSQLDB");
//            add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
//            add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
//            add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, " +
//                    "Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, " +
//                    "Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
//            add("Python: Django.");
//            add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
//            add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
//            add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, " +
//                    "MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, " +
//                    "OAuth2, JWT.");
//            add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix\n" +
//                    "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, " +
//                    "iReport, OpenCmis, Bonita, pgBouncer");
//            add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных " +
//                    "шаблонов, UML, функционального программирования");
//            add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
//            add("Родной русский, английский \"upper intermediate\"");
//        }};
//
//        Company.Period companyPeriod1 = new Company.Period(LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 1),
//                "Инженер по аппаратному и программному тестированию.", "Тестирование, отладка, внедрение ПО цифровой " +
//                "телефонной станции Alcatel 1000 S12\" +\n" +
//                "\" (CHILL, ASM)..\"");
//        Company company1 = new Company("Alcatel","", List.of(companyPeriod1));
//        company1.setWebsite("http://www.alcatel.ru/");
//
//        Company.Period companyPeriod2 = new Company.Period(LocalDate.of(2005, 1, 1), LocalDate.of(2008, 6, 1),
//                "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и " +
//                "отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
//        Company company2 = new Company("Siemens AG","", List.of(companyPeriod2));
//        List<Company> listCompanies = new ArrayList<>() {{
//            add(company1);
//            add(company2);
//        }};
//
//        Company.Period educationPeriod1 = new Company.Period(LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1),
//                "Закончил с отличием", "");
//        Company education = new Company("Заочная физико-техническая школа при МФТИ","", List.of(educationPeriod1));
//
//        Company.Period educationPeriod2 = new Company.Period(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1),
//                "Аспирантура (программист С, С++)", "");
//        Company.Period educationPeriod3 = new Company.Period(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1),
//                "Инженер (программист Fortran, C)", "");
//
//        Company education2 = new Company("Санкт-Петербургский национальный исследовательский университет " +
//                "информационных технологий, механики и оптики","", List.of(educationPeriod2, educationPeriod3));
//
//        List<Company> listEducations = new ArrayList<>() {{
//            add(education);
//            add(education2);
//        }};
//
//        AbstractSection objectiveText = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и " +
//                "Enterprise технологиям");
//        AbstractSection personalText = new TextSection("Аналитический склад ума, сильная логика, " +
//                "креативность, инициативность. Пурист кода и архитектуры");
//
//
//        sections.put(SectionsType.OBJECTIVE, objectiveText);
//        sections.put(SectionsType.PERSONAL, personalText);
//        sections.put(SectionsType.ACHIEVEMENT, new ListSection(textAchievements));
//        sections.put(SectionsType.QUALIFICATIONS, new ListSection(textQualifications));
//        sections.put(SectionsType.EXPERIENCE, new CompanySection(listCompanies));
//        sections.put(SectionsType.EDUCATION, new CompanySection(listEducations));
//
//        resume.setContacts(getContacts());
//        resume.setSections(sections);
//
//        for (ContactsType contact : ContactsType.values()) {
//            System.out.println(resume.getContacts(contact));
//        }
//
//        for (SectionsType section : SectionsType.values()) {
//            System.out.println(resume.getSection(section));
//        }

        return resume;
    }
}