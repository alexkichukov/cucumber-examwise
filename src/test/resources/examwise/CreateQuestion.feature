Feature: Създаване на въпрос
  Като учител
  Искам да мога да създавам различни типове въпроси
  За да мога да ги използвам в изпити

  Background: 
    Given Потребителят е учител и отваря формата за създаване на въпрос

  Scenario: Успешно създаване на затворен въпрос
    When избeре "затворен отговор"
    And въведе "Колко планети има в нашата слънчева система?" в полето за име на въпрос
    And добави следните отговори:
      | Отговор | Верен |
      |       7 | false |
      |       8 | true  |
      |       9 | false |
      |      10 | false |
    And натисне бутона за създаване
    Then вижда съобщение "Успешно създаден въпрос с затворен отговор."

  Scenario: Успешно създаване на отворен въпрос
    When избeре "отворен отговор"
    And въведе "Опишете разликите между комети и астероиди" в полето за име на въпрос
    And натисне бутона за създаване
    Then вижда съобщение "Успешно създаден въпрос с отворен отговор."

  Scenario: Създаване на затворен въпрос без отговори
    When избeре "затворен отговор"
    And въведе "Кой е най-високият връх в света?" в полето за име на въпрос
    And натисне бутона за създаване
    Then вижда съобщение за грешка "Моля добавете поне един отговор."

  Scenario: Създаване на затворен въпрос без правилен отговор
    When избeре "затворен отговор"
    And въведе "Този въпрос няма правилни отговори." в полето за име на въпрос
    And добави следните отговори:
      | Отговор   | Верен |
      | Отговор 1 | false |
      | Отговор 2 | false |
      | Отговор 3 | false |
    And натисне бутона за създаване
    Then вижда съобщение за грешка "Въпросът трябва да съдържа поне един правилен отговор."

  Scenario: Създаване на въпрос с повече от един верен отговор
    When избeре "затворен отговор"
    And въведе "Изберете правилните отговори за следните въпроси." в полето за име на въпрос
    And добави следните отговори:
      | Отговор   | Верен |
      | Отговор 1 | true  |
      | Отговор 2 | true  |
      | Отговор 3 | false |
    And натисне бутона за създаване
    Then вижда съобщение "Успешно създаден въпрос с затворен отговор."

  Scenario: Създаване на въпрос с дублирани отговори
    When избeре "затворен отговор"
    And въведе "Коя е столицата на Франция?" в полето за име на въпрос
    And добави следните отговори:
      | Отговор | Верен |
      | Париж   | true  |
      | Париж   | false |
    And натисне бутона за създаване
    Then вижда съобщение за грешка "Въпросът съдържа дублирани отговори, моля премахнете дубликатите."

  Scenario: Създаване на въпрос без име
    When избeре "затворен отговор"
    And въведе "Допълнителният текст не е задължителен" в полето за допълнителен текст
    And натисне бутона за създаване
    Then вижда съобщение за грешка "Моля добавете име на въпроса."
